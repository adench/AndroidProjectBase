package com.xqkj.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class ScreenShotUtil {
    public static ScreenShotUtil instance;
    public static ScreenShotUtil getInstance(){
        if(instance == null){
            instance = new ScreenShotUtil();
        }
        return instance;
    }


    //截屏
    public Bitmap capturecShareView(View screenView) {
        Bitmap bitmap = getBitmapFromView(screenView);
        return bitmap;
    }


    public Bitmap getBitmapFromView(View view){
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);//ARGB_8888
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        bitmap = compressBitmap(bitmap,100);
        return bitmap;
    }

    public Bitmap thumbBitmap(Bitmap bitmap){
        return compressBitmap(bitmap,50);
    }

    /**
     * 压缩图片
     *
     * @param bitmap
     *          被压缩的图片
     * @param sizeLimit
     *          大小限制
     * @return
     *          压缩后的图片
     */
    private Bitmap compressBitmap(Bitmap bitmap, long sizeLimit) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 80;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

        // 循环判断压缩后图片是否超过限制大小
        while(baos.toByteArray().length / 1024 > sizeLimit) {
            // 清空baos
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10;
            if(quality <= 0){
                break;
            }
        }

        Bitmap newBitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, null);

        return newBitmap;
    }
}
