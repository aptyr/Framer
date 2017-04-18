package com.aptyr.framer.player;

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import com.aptyr.framer.framer.Frame;
import com.aptyr.framer.framer.Framer;
import com.aptyr.framer.framer.XMLFrames;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Player {

    public static final int BASED_ON_XML = -1;

    public interface DataSource {
        Map<Integer, XMLFrames> getFrames();

        Drawable getDrawable(@DrawableRes int resID, Frame frame);
    }

    public interface Delegate {
        void onPlayerCompleted(@DrawableRes int resID);

        void onPlayerError(@DrawableRes int resID, Throwable e);

        void onPlayerCurrentFrame(@DrawableRes int resID, Frame frame);
    }

    private Framer mFramer;

    private DataSource mDataSource;
    private Delegate mDelegate;

    private Subscription mSubscription;

    public Player(Framer framer) {
        mFramer = framer;
    }

    public void setDataSource(DataSource dataSource) {
        mDataSource = dataSource;
    }

    public void setDelegate(Delegate delegate) {
        mDelegate = delegate;
    }

    /**
     * @param resID
     */
    public void play(@DrawableRes final int resID) {
        startPlaying(resID, BASED_ON_XML);
    }

    /**
     * @param resID
     * @param repeatCount
     */
    public void play(@DrawableRes final int resID, final int repeatCount) {
        startPlaying(resID, repeatCount);
    }

    public void stop() {
        if (this.mSubscription != null && !this.mSubscription.isUnsubscribed()) {
            this.mSubscription.unsubscribe();
        }
    }

    private void startPlaying(@DrawableRes final int resID, final int repeatCount) {
        stop();

        Observable observable = Observable.from(mDataSource.getFrames().get(resID).getFrames()).concatMap(new Func1<Frame, Observable<Frame>>() {
            @Override
            public Observable<Frame> call(Frame frame) {
                return Observable.just(frame).delay(frame.getDuration(), TimeUnit.MILLISECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());

        if (repeatCount == BASED_ON_XML) {
            if(this.mDataSource.getFrames().get(resID).isOneshot()) {
                observable = observable.repeat(1);
            } else {
                observable = observable.repeat();
            }
        } else {
            observable = observable.repeat(repeatCount);
        }

        this.mSubscription = observable.subscribe(new Observer<Frame>() {
            @Override
            public void onCompleted() {
                if (mDelegate != null) {
                    mDelegate.onPlayerCompleted(resID);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mDelegate != null) {
                    mDelegate.onPlayerError(resID, e);
                }
            }

            @Override
            public void onNext(Frame frame) {
                if (mDelegate != null) {
                    mDelegate.onPlayerCurrentFrame(resID, frame);
                }

                mFramer.setBackground(mDataSource.getDrawable(resID,frame));
            }
        });
    }


}
