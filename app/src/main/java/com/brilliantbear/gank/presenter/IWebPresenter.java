package com.brilliantbear.gank.presenter;

import android.webkit.WebView;

/**
 * Created by Bear on 2016-5-17.
 */
public interface IWebPresenter {
    void setUpWebView(WebView webView);
    void loadUrl(WebView webView, String url);
    void showProgress();
    void hideProgress();
    void showSth(String msg);
}
