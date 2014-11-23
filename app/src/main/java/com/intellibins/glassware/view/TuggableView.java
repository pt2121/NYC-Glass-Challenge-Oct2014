/*
 * Copyright (c) 2014 Intellibins authors
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of The Intern nor the names of its contributors may
 *       be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE LISTED COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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