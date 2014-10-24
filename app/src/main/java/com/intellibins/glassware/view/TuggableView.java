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

package com.intellibins.glassware.view;

import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by prt2121 on 9/27/14.
 */
public class TuggableView extends CardScrollView {

    private final View mContentView;

    /**
     * Initializes a TuggableView that uses the specified layout
     * resource for its user interface.
     */
    public TuggableView(Context context, int layoutResId) {
        this(context, LayoutInflater.from(context)
                .inflate(layoutResId, null));
    }

    /**
     * Initializes a TuggableView that uses the specified view
     * for its user interface.
     */
    public TuggableView(Context context, View view) {
        super(context);

        mContentView = view;
        setAdapter(new SingleCardAdapter());
        activate();
    }

    /**
     * Overridden to return false so that all motion events still
     * bubble up to the activity's onGenericMotionEvent() method after
     * they are handled by the card scroller. This allows the activity
     * to handle TAP gestures using a GestureDetector instead of the
     * card scroller's OnItemClickedListener.
     */
    @Override
    protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
        super.dispatchGenericFocusedEvent(event);
        return false;
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