package com.aptyr.framer;

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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;

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
        this.mFramesHandler = new FramesHandler(context);
        this.mPlayer = new Player(this);

        this.mPlayer.setDataSource(this);
    }

    public void setBackgroundResources(@DrawableRes int... resIDs) {
        this.mFramesHandler.setBackgroundResources(resIDs);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resID) {
        this.mFramesHandler.setBackgroundResources(resID);
    }

    public Player getPlayer() {
        return this.mPlayer;
    }

    @Override
    public Map<Integer, XMLFrames> getFrames() {
        return this.mFramesHandler.getFrames();
    }

    @Override
    public Drawable getDrawable(@DrawableRes int resID, Frame frame) {
        return this.mFramesHandler.getDrawables().get(resID).get(frame);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mPlayer.stop();
    }
}
