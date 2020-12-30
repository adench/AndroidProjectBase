package com.xqkj.baselibrary.waitdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.xqkj.baselibrary.R;

import static com.xqkj.baselibrary.waitdialog.DialogSettings.THEME_LIGHT;
import static com.xqkj.baselibrary.waitdialog.DialogSettings.tipTextInfo;
import static com.xqkj.baselibrary.waitdialog.DialogSettings.tip_theme;


public class WaitDialog extends BaseDialog {

    private OnBackPressListener onBackPressListener;
    private AlertDialog alertDialog;
    private WaitDialog waitDialog;
    private static boolean isCanCancel = true;

    private View customView;
    private TextInfo customTextInfo;

    private Context context;
    private String tip;

    private WaitDialog() {
    }

    public static WaitDialog show(Context context, String tip) {
        return show(context, tip, null, null, null);
    }

    public static WaitDialog show(Context context, String tip, DialogLifeCycleListener lifeCycleListener) {
        return show(context, tip, null, null, lifeCycleListener);
    }

    public static WaitDialog show(Context context, String tip, View customView) {
        return show(context, tip, customView, null, null);
    }

    public static WaitDialog show(Context context, String tip, View customView, DialogLifeCycleListener lifeCycleListener) {
        return show(context, tip, customView, null, lifeCycleListener);
    }

    public static WaitDialog show(Context context, String tip, TextInfo textInfo) {
        return show(context, tip, null, textInfo, null);
    }

    public static WaitDialog show(Context context, String tip, TextInfo textInfo, DialogLifeCycleListener lifeCycleListener) {
        return show(context, tip, null, textInfo, lifeCycleListener);
    }

    public static WaitDialog show(Context context, String tip, View customView, TextInfo textInfo) {
        return show(context, tip, customView, textInfo, null);
    }

    public static WaitDialog show(Context context, String tip, View customView, TextInfo textInfo, DialogLifeCycleListener lifeCycleListener) {
        synchronized (WaitDialog.class) {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.cleanDialogLifeCycleListener();
            waitDialog.context = context;
            waitDialog.tip = tip;
            waitDialog.log("装载等待对话框 -> " + tip);
            waitDialog.waitDialog = waitDialog;
            waitDialog.customView = customView;
            waitDialog.customTextInfo = textInfo;
            waitDialog.setDialogLifeCycleListener(lifeCycleListener);
            waitDialog.showDialog();
            return waitDialog;
        }
    }

    private int font_color;

    private RelativeLayout boxInfo;
    private RelativeLayout boxBkg;
    private RelativeLayout boxProgress;
    private ProgressView progress;
    private TextView txtInfo;

    private KongzueDialogHelper kongzueDialogHelper;

    public void showDialog() {
        if (customTextInfo == null) {
            customTextInfo = tipTextInfo;
        }

        dialogList.add(waitDialog);
        log("显示等待对话框 -> " + tip);

        AlertDialog.Builder builder;
        int bkgResId;
        switch (tip_theme) {
            case THEME_LIGHT:
                builder = new AlertDialog.Builder(context, R.style.waitDialog_lightMode);
                bkgResId = R.drawable.rect_light;
                font_color = Color.rgb(0, 0, 0);
                break;
            default:
                builder = new AlertDialog.Builder(context, R.style.waitDialog_darkMode);
                bkgResId = R.drawable.rect_dark;
                font_color = Color.rgb(255, 255, 255);
                break;
        }

        alertDialog = builder.create();

        getDialogLifeCycleListener().onCreate(alertDialog);
        if (isCanCancel) alertDialog.setCanceledOnTouchOutside(true);

        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        kongzueDialogHelper = new KongzueDialogHelper().setAlertDialog(alertDialog, new OnDismissListener() {
            @Override
            public void onDismiss() {
                dialogList.remove(waitDialog);
                if (boxProgress != null) boxProgress.removeAllViews();
                if (boxBkg != null) boxBkg.removeAllViews();

                getDialogLifeCycleListener().onDismiss();
                alertDialog = null;
                getOnDismissListener().onDismiss();
            }
        });

        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
        alertDialog.setView(rootView);

        boxInfo = rootView.findViewById(R.id.box_info);
        boxBkg = rootView.findViewById(R.id.box_bkg);
        boxProgress = rootView.findViewById(R.id.box_progress);
        progress = rootView.findViewById(R.id.progress);
        txtInfo = rootView.findViewById(R.id.txt_info);

        txtInfo.setTextColor(font_color);

        if (customView != null) {
            progress.setVisibility(View.GONE);
            boxProgress.removeAllViews();
            boxProgress.addView(customView);
        }
        if (tip_theme == THEME_LIGHT) {
            progress.setStrokeColors(new int[]{Color.rgb(0, 0, 0)});
        } else {
            progress.setStrokeColors(new int[]{Color.rgb(255, 255, 255)});
        }
//        progress.setStrokeColors(new int[]{Color.rgb(3, 137, 255)});

        boxBkg.setBackgroundResource(bkgResId);

        if (tip != null && !tip.isEmpty()) {
            txtInfo.setVisibility(View.VISIBLE);
            txtInfo.setText(tip);
        } else {
            txtInfo.setVisibility(View.GONE);

            RelativeLayout.LayoutParams lp = ((RelativeLayout.LayoutParams) boxProgress.getLayoutParams());
            lp.setMargins(0, 0, 0, 0);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            boxProgress.setLayoutParams(lp);

        }

        if (customTextInfo.getFontSize() > 0) {
            txtInfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, customTextInfo.getFontSize());
        }
        if (customTextInfo.getFontColor() != 1) {
            txtInfo.setTextColor(customTextInfo.getFontColor());
        }
        if (customTextInfo.getGravity() != -1) {
            txtInfo.setGravity(customTextInfo.getGravity());
        }

        Typeface font = Typeface.create(Typeface.SANS_SERIF, customTextInfo.isBold() ? Typeface.BOLD : Typeface.NORMAL);
        txtInfo.setTypeface(font);

        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (onBackPressListener != null) {
                            onBackPressListener.OnBackPress(alertDialog);
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        getDialogLifeCycleListener().onShow(alertDialog);

        kongzueDialogHelper.show(fragmentManager, "kongzueDialog");
        kongzueDialogHelper.setCancelable(isCanCancel);

    }

    @Override
    public void doDismiss() {
        if (kongzueDialogHelper != null) kongzueDialogHelper.dismissAllowingStateLoss();
    }

    public WaitDialog setCanCancel(boolean canCancel) {
        if (kongzueDialogHelper != null) kongzueDialogHelper.setCancelable(canCancel);
        return this;
    }

    public static void setCanCancelGlobal(boolean canCancel) {
        isCanCancel = canCancel;
    }

    public WaitDialog setOnBackPressListener(OnBackPressListener onBackPressListener) {
        this.onBackPressListener = onBackPressListener;
        return this;
    }

    public static void dismiss() {
        if (dialogList != null && dialogList.size() > 0) {
            for (int i = 0;i < dialogList.size();i ++){
                BaseDialog dialog = dialogList.get(i);
                if (dialog instanceof WaitDialog) {
                    dialog.doDismiss();
                }
            }
        }
    }

    public void setText(String s) {
        if (waitDialog != null && waitDialog.txtInfo != null) {
            waitDialog.txtInfo.setText(s);
        }
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }
}
