package com.intellibins.glassware;

import com.intellibins.glassware.view.TuggableView;
import com.prt2121.glass.widget.SliderView;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends BaseGlassActivity {

    private TuggableView mTuggableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTuggableView = new TuggableView(this, R.layout.activity_splash_screen);
        setContentView(mTuggableView);
        SliderView slider = (SliderView) mTuggableView.findViewById(R.id.progressBar);
        // TODO
        slider.startProgress(20000, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashScreenActivity.this, MenuActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

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

}
