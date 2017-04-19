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
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import java.util.HashMap;
import java.util.Map;

class FramesHandler {

    private Map<Integer, Map<Frame, Drawable>> drawables = new HashMap<>();
    private Map<Integer, XMLFrames> frames = new HashMap<>();

    private XmlParser mXmlParser;
    private Resources mResources;

    public FramesHandler(Context context) {
        this.mXmlParser = new XmlParser(context);
        this.mResources = context.getResources();
    }

    public void setBackgroundResources(@DrawableRes int... resIDs){

        recycle();

        for (int resID : resIDs) {
            Map<Frame, Drawable> drawables = new HashMap<>();

            XMLFrames xmlFrames = this.mXmlParser.getFrames(resID);

            for (Frame frame : xmlFrames.getFrames()) {
                drawables.put(frame, mResources.getDrawable(frame.getResourceId()));
            }

            this.drawables.put(resID, drawables);
            this.frames.put(resID, xmlFrames);
        }

    }

    public Map<Integer, Map<Frame, Drawable>> getDrawables() {
        return this.drawables;
    }

    public Map<Integer, XMLFrames> getFrames() {
        return this.frames;
    }

    private void recycle() {

        for (Integer resID : drawables.keySet()) {
            for (Frame frame : drawables.get(resID).keySet()) {
                drawables.get(resID).get(frame).setCallback(null);
            }
        }

        drawables.clear();
        frames.clear();
    }
}
