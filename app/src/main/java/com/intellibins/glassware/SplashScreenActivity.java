package com.intellibins.glassware;

import com.intellibins.glassware.view.TuggableView;
import com.prt2121.glass.widget.SliderView;

import android.os.Bundle;

public class SplashScreenActivity extends BaseGlassActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TuggableView view = new TuggableView(this, R.layout.activity_splash_screen);
        setContentView(view);
        SliderView slider = (SliderView) view.findViewById(R.id.progressBar);
        slider.startIndeterminate();
    }

}
