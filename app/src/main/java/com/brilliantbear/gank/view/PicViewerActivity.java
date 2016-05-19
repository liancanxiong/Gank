package com.brilliantbear.gank.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.adapter.ViewerAdapter;
import com.brilliantbear.gank.custom.PinchImageViewPager;

import java.util.ArrayList;

import butterknife.Bind;

public class PicViewerActivity extends BaseActivity {

    @Bind(R.id.pager)
    PinchImageViewPager mPaper;

    public static final String KEY_URLS = "urls";
    public static final String KEY_POSITION = "position";
    private ArrayList<String> mUrls;

    public static void gotoViewer(Context context, ArrayList<String> urls, int position) {
        Intent intent = new Intent(context, PicViewerActivity.class);
        intent.putExtra(KEY_URLS, urls);
        intent.putExtra(KEY_POSITION, position);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_viewer;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Intent intent = getIntent();
        mUrls = intent.getStringArrayListExtra(KEY_URLS);
        int position = intent.getIntExtra(KEY_POSITION, 0);
        ViewerAdapter mAdapter = new ViewerAdapter(mContext, mUrls);
        mPaper.setAdapter(mAdapter);
        mPaper.setCurrentItem(position);
    }
}
