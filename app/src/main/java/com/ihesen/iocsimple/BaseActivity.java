package com.ihesen.iocsimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ihesen.ioclibrary.InjectManager;

/**
 * Created by ihesen on 2019-05-07
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //帮助子类进行布局、空间、时间的注入
        InjectManager.inject(this);
    }
}
