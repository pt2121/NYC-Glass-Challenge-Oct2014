package com.intellibins.glassware;

import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardBuilder;

import com.intellibins.glassware.model.Loc;
import com.intellibins.glassware.view.TuggableView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import de.greenrobot.event.EventBus;

public class MenuActivity extends BaseGlassActivity {

    private static final String TAG = MenuActivity.class.getSimpleName();

    private TuggableView mTuggableView;

    private View mView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mView = buildView();

        mTuggableView = new TuggableView(this, mView);

        getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);
        setContentView(mTuggableView);

        // TODO
        DataEvent dataEvent = EventBus.getDefault().removeStickyEvent(DataEvent.class);
        if (dataEvent != null && dataEvent.locs != null) {
            for (Loc l : dataEvent.locs) {
                Log.d(TAG, l.name);
            }
        }

        ImageEvent imageEvent = EventBus.getDefault().removeStickyEvent(ImageEvent.class);
        if(!TextUtils.isEmpty(imageEvent.filePath)) {
            Log.d(TAG, imageEvent.filePath);
        }
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

    private View buildView() {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);

        card.setText("Whatever");
        return card.getView();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            switch (item.getItemId()) {
                case R.id.special_waste_menu_item:
                    break;
                default:
                    return true;
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected boolean onTap() {
        openOptionsMenu();
        return true;
    }

}
