package com.xqkj.projectbase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xqkj.baselibrary.utils.DataUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataUtils.formatData(1001);
    }
}
