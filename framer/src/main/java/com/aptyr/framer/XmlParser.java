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
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.annotation.DrawableRes;
import android.support.annotation.XmlRes;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class XmlParser {

    private Resources resources;

    public XmlParser(Context context) {
        resources = context.getResources();
    }

    public List<Frame> getFrames(@XmlRes int resID) {
        List<Frame> frames = new ArrayList<>();

        try {
            XmlResourceParser parser = resources.getXml(resID);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {

                    if (parser.getName().equals("item")) {
                        int duration = 1000;
                        @DrawableRes int resId = 0;

                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            if (parser.getAttributeName(i).equals("drawable")) {
                                resId = Integer.parseInt(parser.getAttributeValue(i).substring(1));
                            } else if (parser.getAttributeName(i).equals("duration")) {
                                duration = parser.getAttributeIntValue(i, 1000);
                            }
                        }

                        frames.add(new Frame(resId, duration));


                    }

                } else if (eventType == XmlPullParser.END_TAG) {

                } else if (eventType == XmlPullParser.TEXT) {

                }

                eventType = parser.next();
            }


        } catch (IOException | XmlPullParserException e) {

        }

        return frames;
    }
}
