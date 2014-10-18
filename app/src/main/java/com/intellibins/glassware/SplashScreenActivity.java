package com.intellibins.glassware;

import com.google.android.glass.media.Sounds;

import com.intellibins.glassware.view.TuggableView;
import com.prt2121.glass.widget.SliderView;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

public class SplashScreenActivity extends BaseGlassActivity {

    private TuggableView mTuggableView;

    private SliderView mSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTuggableView = new TuggableView(this, R.layout.activity_loading);
        setContentView(mTuggableView);
        mSlider = (SliderView) mTuggableView.findViewById(R.id.progressBar);
        // TODO
        mSlider.startProgress(4000, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashScreenActivity.this, CaptureActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO
                //finish();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        startService(new Intent(this, DataService.class));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mTuggableView.activate();
    }

    @Override
    protected void onPause() {
        mTuggableView.deactivate();
        super.onPause();
    }

    @Override
    protected boolean onTap() {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audio.playSoundEffect(Sounds.DISALLOWED);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*@Override
    protected boolean onSwipeDown() {
        return super.onSwipeDown();
    }*/

}
