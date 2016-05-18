package com.brilliantbear.gank.presenter;

import android.webkit.WebView;

import com.brilliantbear.gank.model.IWebModel;
import com.brilliantbear.gank.model.WebModel;
import com.brilliantbear.gank.view.IWebView;

/**
 * Created by Bear on 2016-5-17.
 */
public class WebPresenter implements IWebPresenter{

    private IWebView view;
    private final IWebModel webModel;

    public WebPresenter(IWebView webView) {
        this.view = webView;
        webModel = new WebModel(this);
    }

    @Override
    public void setUpWebView(WebView webView) {
        webModel.setUpWebView(webView);
    }

    @Override
    public void loadUrl(WebView webView, String url) {
        webModel.loadUrl(webView, url);
    }

    @Override
    public void showProgress() {
        view.showProgress();
    }

    @Override
    public void hideProgress() {
        view.hideProgress();
    }

    @Override
    public void showSth(String msg) {
        view.showSth(msg);
    }
}
