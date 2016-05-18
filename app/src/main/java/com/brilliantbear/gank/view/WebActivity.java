package com.brilliantbear.gank.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.webkit.WebView;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.presenter.IWebPresenter;
import com.brilliantbear.gank.presenter.WebPresenter;
import com.brilliantbear.gank.utils.DensityUtils;

import butterknife.Bind;

/**
 * Created by Bear on 2016-5-17.
 */
public class WebActivity extends BaseActivity implements IWebView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.web)
    WebView mWeb;

    public static final String KEY_URL = "url";
    public static final String KEY_TITLE = "title";
    private IWebPresenter mWebPresenter;

    public static void gotoWebActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    private void showRefresh(boolean isShow) {
        if (mRefresh == null)
            return;
        if (isShow) {
            mRefresh.setProgressViewOffset(false, 0, DensityUtils.dip2px(mContext, 24));
            mRefresh.setRefreshing(true);
        } else {
            mRefresh.setRefreshing(false);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        Intent intent = getIntent();
        String title = intent.getStringExtra(KEY_TITLE);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        mRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        Intent intent = getIntent();
        String url = intent.getStringExtra(KEY_URL);

        mWebPresenter = new WebPresenter(this);
        mWebPresenter.setUpWebView(mWeb);
        mWebPresenter.loadUrl(mWeb, url);
    }

    @Override
    public void showSth(String msg) {
        Snackbar.make(mWeb, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        showRefresh(false);
    }

    @Override
    public void showProgress() {
        showRefresh(true);
    }

    @Override
    public void onBackPressed() {
        if (mWeb.canGoBack()) {
            mWeb.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onRefresh() {
        mWeb.reload();
    }
}
