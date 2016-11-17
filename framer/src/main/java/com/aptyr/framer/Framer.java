package com.aptyr.framer;

/*
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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

public class Framer extends ImageView implements Player.DataSource {
    private FramesHandler mFramesHandler;
    private Player mPlayer;

    public Framer(Context context) {
        super(context);
        init(context);
    }

    public Framer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mFramesHandler = new FramesHandler(context);
        mPlayer = new Player(this);

        mPlayer.setDataSource(this);

    }

    public void setBackgroundResources(@DrawableRes int... resIDs) {
        mFramesHandler.setBackgroundResources(resIDs);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resID) {
        mFramesHandler.setBackgroundResources(resID);
    }

    public Player getPlayer() {
        return mPlayer;
    }

    @Override
    public Map<Integer, List<Frame>> getFrames() {
        return mFramesHandler.getFrames();
    }

    @Override
    public Map<Integer, Map<Frame, Drawable>> getFramesWithDrawables() {
        return mFramesHandler.getFramesWithDrawables();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPlayer.stop();
    }
}
