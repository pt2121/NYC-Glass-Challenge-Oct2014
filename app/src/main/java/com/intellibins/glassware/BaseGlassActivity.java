package com.intellibins.glassware;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

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

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {
                if (gesture == Gesture.TAP || gesture == Gesture.LONG_PRESS) {
                    Log.v(TAG, "onSwipeTap");
                    return onTap();
                } else if (gesture == Gesture.TWO_TAP) {
                    Log.v(TAG, "onSwipeTwoTap");
                    return onTwoTap();
                } else if (gesture == Gesture.SWIPE_RIGHT) {
                    Log.v(TAG, "onSwipeRight");
                    return onSwipeRight();
                } else if (gesture == Gesture.SWIPE_LEFT) {
                    Log.v(TAG, "onSwipeLeft");
                    return onSwipeLeft();
                }
                return false;
            }
        });
        return gestureDetector;
    }
}
