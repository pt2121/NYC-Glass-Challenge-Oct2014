package com.intellibins.glassware;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import com.intellibins.glassware.model.Loc;
import com.prt2121.glass.widget.SliderView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by prt2121 on 10/7/14.
 */
public class ResultActivity extends BaseGlassActivity {

    private static final String TAG = ResultActivity.class.getSimpleName();

    private List<CardBuilder> mCards = new ArrayList<CardBuilder>();

    private List<Loc> mLocs = new ArrayList<Loc>();

    private CardScrollView mCardScrollView;

    private ResultCardScrollAdapter mAdapter;

    private View mContentView;

    private int mDrawable = R.drawable.bin_plastic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            String type = intent.getStringExtra(CaptureActivity.ITEM_TYPE);
            if (type.equalsIgnoreCase(CaptureActivity.ITEM_PAPER)) {
                mDrawable = R.drawable.bin_paper;
            }
        }

        mContentView = LayoutInflater.from(ResultActivity.this).inflate(R.layout.activity_loading,
                null);
        mCardScrollView = new CardScrollView(this);
        mCardScrollView.setAdapter(new SingleCardAdapter());
        SliderView slider = (SliderView) mContentView.findViewById(R.id.progressBar);
        slider.startIndeterminate();

        mCardScrollView.activate();
        setContentView(mCardScrollView);

        DataEvent dataEvent = EventBus.getDefault().getStickyEvent(DataEvent.class);
        if (dataEvent != null && dataEvent.locs != null) {
            onEvent(dataEvent);
        } else {
            EventBus.getDefault().registerSticky(this);
        }
    }

    public void onEvent(DataEvent event) {
        EventBus.getDefault().removeStickyEvent(DataEvent.class);
        mLocs = event.locs;
        createCards(mLocs);
        mAdapter = new ResultCardScrollAdapter();
        mCardScrollView.setAdapter(mAdapter);
        mCardScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audio.playSoundEffect(Sounds.TAP);
                Loc l = mLocs.get(position);
                startNavigation(l.latitude, l.longitude);
            }
        });
    }

    private void startNavigation(double lat, double lng) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("google.navigation:mode=w&q=" + lat + "," + lng));
        startActivity(intent);
        finish();
    }

    private void createCards(List<Loc> locs) {
        for (Loc loc : locs) {
            String path = (TextUtils.isEmpty(loc.image)) ?
                    "" :
                    FileUtil.getCacheFilePath(this, loc.image);
            CardBuilder builder = new CardBuilder(this, CardBuilder.Layout.COLUMNS)
                    .setText(loc.name)
                    .setFootnote(loc.address);
            if (TextUtils.isEmpty(path)) {
                builder.addImage(mDrawable);
            } else {
                builder.addImage(Drawable.createFromPath(path));
            }
            mCards.add(builder);
        }
    }

    private class ResultCardScrollAdapter extends CardScrollAdapter {

        @Override
        public int getPosition(Object item) {
            return mCards.indexOf(item);
        }

        @Override
        public int getCount() {
            return mCards.size();
        }

        @Override
        public Object getItem(int position) {
            return mCards.get(position);
        }

        @Override
        public int getViewTypeCount() {
            return CardBuilder.getViewTypeCount();
        }

        @Override
        public int getItemViewType(int position) {
            return mCards.get(position).getItemViewType();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return mCards.get(position).getView(convertView, parent);
        }
    }

    /**
     * Holds the single "card" inside the card scroll view.
     */
    private class SingleCardAdapter extends CardScrollAdapter {

        @Override
        public int getPosition(Object item) {
            return 0;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return mContentView;
        }

        @Override
        public View getView(int position, View recycleView,
                ViewGroup parent) {
            return mContentView;
        }
    }
}
