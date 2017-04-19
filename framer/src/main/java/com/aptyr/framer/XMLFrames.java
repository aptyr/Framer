package com.aptyr.framer;

/**
 * Copyright (C) 2017 Aptyr (github.com/aptyr)
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

import java.util.List;

public class XMLFrames {

    private boolean oneshot;
    private List<Frame> frames;

    private XMLFrames(Builder builder) {
        this.oneshot = builder.oneshot;
        this.frames = builder.frames;
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    public boolean isOneshot() {
        return this.oneshot;
    }


    public static final class Builder {
        private boolean oneshot;
        private List<Frame> frames;

        public Builder() {}

        public Builder oneshot(boolean val) {
            this.oneshot = val;
            return this;
        }

        public Builder frames(List<Frame> val) {
            this.frames = val;
            return this;
        }

        public XMLFrames build() {
            return new XMLFrames(this);
        }
    }
}
