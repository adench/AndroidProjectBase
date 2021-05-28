package com.xqkj.baselibrary.weight;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private boolean isHave_left_right = true;

    public HorizontalItemDecoration(int space) {
        this.space = space;
    }

    public HorizontalItemDecoration(int space, boolean isHave_left_right) {
        this.space = space;
        this.isHave_left_right = isHave_left_right;
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
        if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {

            if (position == 0) {
                if (isHave_left_right) {
                    outRect.left = space;
                } else {
                    outRect.left = 0;
                }
                outRect.right = 0;
            } else if (position == childSize - 1) {
                //最后一个
                outRect.left = space;
                if (isHave_left_right) {
                    outRect.right = space;
                } else {
                    outRect.right = 0;
                }
            } else {
                outRect.left = space;
                outRect.right = 0;
            }
        }
    }
}