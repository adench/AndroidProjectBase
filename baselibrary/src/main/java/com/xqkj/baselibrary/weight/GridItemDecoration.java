package com.xqkj.baselibrary.weight;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.xqkj.baselibrary.utils.DensityUtils;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int verticalSpace;
    private int horizontalSpace;
    private boolean includeEdge = false;

    public GridItemDecoration(int spanCount, int space) {
        this(spanCount,space,space,false);
    }

    public GridItemDecoration(int spanCount, int verticalSpace, int horizontalSpace) {
        this(spanCount,verticalSpace,horizontalSpace,false);
    }

    public GridItemDecoration(int spanCount, int space,boolean includeEdge) {
        this(spanCount,space,space,includeEdge);
    }

    public GridItemDecoration(int spanCount, int verticalSpace, int horizontalSpace, boolean includeEdge) {
        this.spanCount = spanCount;
        this.verticalSpace = DensityUtils.dp2px(verticalSpace);
        this.horizontalSpace = DensityUtils.dp2px(horizontalSpace);
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = verticalSpace - column * verticalSpace / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * verticalSpace / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = horizontalSpace;
            }
            outRect.bottom = horizontalSpace; // item bottom
        } else {
            outRect.left = column * verticalSpace / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = verticalSpace - (column + 1) * verticalSpace / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = horizontalSpace; // item top
            }
        }
    }
}
