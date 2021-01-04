package com.xqkj.projectbase.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CenterAddActivity extends BaseActivity {
    @BindView(R.id.iv_add)
    ImageView iv_add;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_center_add;
    }

    @Override
    protected boolean isImmersionBar() {
        return true;
    }

    @Override
    protected void initView() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_add, "rotation", 0, 45);
        animator.setDuration(1000);
        animator.start();
    }

    @OnClick({R.id.iv_add})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_add:
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv_add, "rotation", 45, 0);
                animator.setDuration(1000);
                animator.start();
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        CenterAddActivity.this.finish();
                    }
                });

                break;
        }
    }

    @Override
    protected void initData() {

    }
}
