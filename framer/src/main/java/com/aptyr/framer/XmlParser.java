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
import android.content.res.XmlResourceParser;
import android.support.annotation.XmlRes;
import android.util.Pair;

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

    public XMLFrames getFrames(@XmlRes int resID) {

        XMLFrames.Builder framesBuilder = new XMLFrames.Builder();

        try {
            List<Frame> frames = new ArrayList<>();

            XmlResourceParser parser = this.resources.getXml(resID);
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {

                    if (parser.getName().equals(XMLAttribute.ITEM)) {

                        frames.add(new Frame(getItemAttributes(parser)));

                    } else if (parser.getName().equals(XMLAttribute.ANIMATION_LIST)) {

                        framesBuilder.oneshot(getOneshotAttribute(parser));

                    }

                }

                eventType = parser.next();
            }

            framesBuilder.frames(frames);

        } catch (IOException | XmlPullParserException e) {

        }

        return framesBuilder.build();
    }


    private Pair<Integer, Integer> getItemAttributes(XmlResourceParser parser) {

        int duration = 0;
        int resId = 0;

        for (int i = 0; i < parser.getAttributeCount(); ++i) {
            if (parser.getAttributeName(i).equals(XMLAttribute.DRAWABLE)) {
                resId = Integer.parseInt(parser.getAttributeValue(i).substring(1));
            } else if (parser.getAttributeName(i).equals(XMLAttribute.DURATION)) {
                duration = parser.getAttributeIntValue(i, XMLAttribute.DURATION_DEFAULT);
            }
        }

        return new Pair<>(resId, duration);

    }

    private boolean getOneshotAttribute(XmlResourceParser parser) {
        for (int i = 0; i < parser.getAttributeCount(); i++) {

            if(parser.getAttributeName(i).equals(XMLAttribute.ONESHOT)) {
                return parser.getAttributeBooleanValue(i, true);
            }
        }
        return true;
    }

}
