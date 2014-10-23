/*
 * *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.intellibins.glassware;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by prt2121 on 9/27/14.
 */
public class BaseGlassActivity extends Activity {

    private static final String TAG = BaseGlassActivity.class.getSimpleName();

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGestureDetector = createGestureDetector(this);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }

    protected boolean onTap() {
        return false;
    }

    protected boolean onTwoTap() {
        return false;
    }

    protected boolean onSwipeRight() {
        return false;
    }

    protected boolean onSwipeLeft() {
        return false;
    }

    protected boolean onSwipeDown() {
        return false;
    }

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);

        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {
                if (gesture == Gesture.TAP || gesture == Gesture.LONG_PRESS) {
                    return onTap();
                } else if (gesture == Gesture.TWO_TAP) {
                    return onTwoTap();
                } else if (gesture == Gesture.SWIPE_RIGHT) {
                    return onSwipeRight();
                } else if (gesture == Gesture.SWIPE_LEFT) {
                    return onSwipeLeft();
                } else if (gesture == Gesture.SWIPE_DOWN) {
                    return onSwipeDown();
                }
                return false;
            }
        });
        return gestureDetector;
    }
}
