package com.xqkj.baselibrary.weight;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;


public class ScrollerGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public ScrollerGridLayoutManager(Context context, int spanCount) {
        super(context,spanCount);
    }

    public ScrollerGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context,spanCount,orientation, reverseLayout);
    }

    public ScrollerGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
