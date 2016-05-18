package com.brilliantbear.gank.model;

import android.webkit.WebView;

/**
 * Created by Bear on 2016-5-17.
 */
public interface IWebModel {
    void setUpWebView(WebView webView);
    void loadUrl(WebView webView, String url);
}
