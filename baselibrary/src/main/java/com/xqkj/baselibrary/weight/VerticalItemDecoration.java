package com.xqkj.baselibrary.weight;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private boolean isHave_top_bottom = true;

    public VerticalItemDecoration(int space) {
        this.space = space;
    }

    public VerticalItemDecoration(int space, boolean isHave_top_bottom) {
        this.space = space;
        this.isHave_top_bottom = isHave_top_bottom;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        final double childSize = layoutManager.getItemCount();
        double position = parent.getChildAdapterPosition(view);
        //竖直方向的
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {

            if (position == 0) {
                if (isHave_top_bottom) {
                    outRect.top = space;
                } else {
                    outRect.top = 0;
                }
                outRect.bottom = 0;
            } else if (position == childSize - 1) {
                //最后一个
                outRect.top = space;
                if (isHave_top_bottom) {
                    outRect.bottom = space;
                } else {
                    outRect.bottom = 0;
                }
            } else {
                outRect.top = space;
                outRect.bottom = 0;
            }
        }
    }
}