package com.aptyr.framer.framer;

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

import android.support.annotation.DrawableRes;
import android.util.Pair;

public class Frame {

    @DrawableRes  private int resourceId;
    private int duration;

    public Frame(@DrawableRes int resourceId, int duration) {
        this.resourceId = resourceId;
        this.duration = duration;
    }

    public Frame(Pair<Integer, Integer> attributes) {
        this.resourceId = attributes.first;
        this.duration = attributes.second;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "resourceId=" + this.resourceId +
                ", duration=" + this.duration +
                '}';
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getDuration() {
        return duration;
    }
}
