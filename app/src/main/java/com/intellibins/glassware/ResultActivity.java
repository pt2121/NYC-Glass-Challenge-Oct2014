package com.intellibins.glassware;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import com.intellibins.glassware.model.Loc;
import com.prt2121.glass.widget.SliderView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by prt2121 on 10/7/14.
 */
public class ResultActivity extends BaseGlassActivity {

    private static final String TAG = ResultActivity.class.getSimpleName();

    private List<CardBuilder> mCards = new ArrayList<CardBuilder>();

    private CardScrollView mCardScrollView;

    private ResultCardScrollAdapter mAdapter;

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContentView = LayoutInflater.from(ResultActivity.this).inflate(R.layout.activity_loading,
                null);
        mCardScrollView = new CardScrollView(this);
        mCardScrollView.setAdapter(new SingleCardAdapter());
        SliderView slider = (SliderView) mContentView.findViewById(R.id.progressBar);
        slider.startIndeterminate();

        mCardScrollView.activate();
        setContentView(mCardScrollView);

        // TODO
        DataEvent dataEvent = EventBus.getDefault().getStickyEvent(DataEvent.class);
        if (dataEvent != null && dataEvent.locs != null) {
            onEvent(dataEvent);
        } else {
            EventBus.getDefault().registerSticky(this);
        }

//        ImageEvent imageEvent = EventBus.getDefault().removeStickyEvent(ImageEvent.class);
//        if(!TextUtils.isEmpty(imageEvent.filePath)) {
//            Log.d(TAG, imageEvent.filePath);
//        }

//        mCardScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "postion " + position);
//            }
//        });

    }

    public void onEvent(DataEvent event) {
        EventBus.getDefault().removeStickyEvent(DataEvent.class);
        createCards(event.locs);
        mAdapter = new ResultCardScrollAdapter();
        mCardScrollView.setAdapter(mAdapter);
    }

    private void createCards(List<Loc> locs) {
        for (Loc loc : locs) {
            mCards.add(new CardBuilder(this, CardBuilder.Layout.COLUMNS)
                    .setText(loc.name)
                    .setFootnote(loc.address)
                    .addImage(R.drawable.bin_plastic));
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
