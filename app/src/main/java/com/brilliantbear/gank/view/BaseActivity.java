package com.brilliantbear.gank.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.brilliantbear.gank.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initToolbar();
    }

    protected void initData(Bundle savedInstanceState) {

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected abstract int getLayoutId();
}
