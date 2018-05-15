package com.kapil.sample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kapil.sample.R;

/**
 * Created by kapilsharma on 11/07/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void initializeToolbar(boolean backBtnEnabled, String title, boolean isEleavted) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backBtnEnabled);
        if (android.os.Build.VERSION.SDK_INT >= 21 && isEleavted) {
            getSupportActionBar().setElevation(10);
        }
    }
}
