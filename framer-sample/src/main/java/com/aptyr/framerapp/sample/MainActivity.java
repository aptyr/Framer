package com.aptyr.framerapp.sample;

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

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;

import com.aptyr.framer.Frame;
import com.aptyr.framer.Framer;
import com.aptyr.framer.Player;
import com.aptyr.framerapp.R;

public class MainActivity extends AppCompatActivity implements Player.Delegate {

    private Framer framer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        framer = (Framer) findViewById(R.id.framer);
        framer.setBackgroundResources(R.drawable.jump_animation_fast, R.drawable.jump_animation_slow);

        framer.getPlayer().setDelegate(this);

        framer.getPlayer().play(R.drawable.jump_animation_fast);

        CountDownTimer cdt = new CountDownTimer(3000, 3000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                framer.getPlayer().stop();
            }

        }.start();
    }

    @Override
    public void onPlayerCompleted(@DrawableRes int resID) {
        if (resID == R.drawable.jump_animation_fast) {
            framer.getPlayer().play(R.drawable.jump_animation_slow,2);
        }
    }

    @Override
    public void onPlayerError(@DrawableRes int resID, Throwable e) {
    }

    @Override
    public void onPlayerCurrentFrame(@DrawableRes int resID, Frame frame) {
    }
}
