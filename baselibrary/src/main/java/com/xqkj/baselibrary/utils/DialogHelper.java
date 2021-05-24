package com.xqkj.baselibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xqkj.baselibrary.R;
import com.xqkj.baselibrary.base.BaseApplication;
import com.xqkj.baselibrary.listener.DialogCommitListener;

public class DialogHelper {
    public static MaterialDialog.Builder getDialog(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        return builder;
    }

    /**
     * 自定义布局
     **/
    public static MaterialDialog getCustomDialog(Context context, int layoutId) {
        MaterialDialog.Builder builder = getDialog(context);
        MaterialDialog dialog = builder.customView(layoutId, false).build();
        dialog.show();
        return dialog;
    }

    /**
     * 圆角dialog
     *
     * @param layoutId
     * @return
     */
    public static MaterialDialog getConnerBgDialog(Context mContext, int layoutId) {
        MaterialDialog dialog = getCustomDialog(mContext, R.layout.dialog_conner_layout);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = BaseApplication.widthPixels - 2 * DensityUtils.dp2px(50);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(params);

        View dialogView = dialog.getCustomView();
        LinearLayout contentLayout = dialogView.findViewById(R.id.ll_dialog_content);
        contentLayout.addView(LayoutInflaterUtils.from(mContext, layoutId));
        return dialog;
    }

    public static MaterialDialog showContentDialog(Context context, String title, String content, final DialogCommitListener listener) {
        final MaterialDialog dialog = getConnerBgDialog(context, R.layout.dialog_content_layout);
        View view = dialog.getCustomView();
        TextView tvTitle = view.findViewById(R.id.title);
        TextView tvContent = view.findViewById(R.id.content);
        TextView tv_cancal = view.findViewById(R.id.tv_cancal);
        TextView tv_commit = view.findViewById(R.id.tv_commit);

        if(!TextUtils.isEmpty(title))
        tvTitle.setText(title);
        if(!TextUtils.isEmpty(content))
        tvContent.setText(content);
        tv_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (listener != null)
                    listener.commit();
            }
        });
        return dialog;
    }

    //提示信息
    public static void showTipDialog(Context context, String title, String content) {
        if(TextUtils.isEmpty(content))return;
        MaterialDialog dialog = getConnerBgDialog(context, R.layout.dialog_tip);
        View view = dialog.getCustomView();
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(content);
        TextView tv_title = view.findViewById(R.id.tv_title);
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
    }

}
