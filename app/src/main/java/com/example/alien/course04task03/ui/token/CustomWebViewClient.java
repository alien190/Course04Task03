package com.example.alien.course04task03.ui.token;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class CustomWebViewClient extends WebViewClient {

    private IOnAuthCallback mOnAuthCallback;
    private IOnNeedShowCallback mOnNeedShowCallback;

    private List<String> allowedPath = new ArrayList<String>() {{
        add("/login/oauth/authorize");
        add("/login");
        add("/password_reset");
        add("/join");
        add("/explore");
    }};

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return checkRequestURL(request.getUrl());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return checkRequestURL(Uri.parse(url));
    }

    private boolean checkRequestURL(Uri uri) {

        Timber.d("CustomWebViewClient.checkRequestURL: %s", uri.toString());

        if (uri.getHost().equals("github.com")
                && allowedPath.contains(uri.getPath())) {

            if (mOnNeedShowCallback != null) {
                mOnNeedShowCallback.onNeedShow();
            }
            return false;
        } else {
            if (mOnAuthCallback != null) {
                mOnAuthCallback.onAuthComplete(uri.getQueryParameter("code"),
                        uri.getQueryParameter("state"));
            }
            return true;
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);

    }

    interface IOnAuthCallback {
        void onAuthComplete(String code, String state);
    }

    interface IOnNeedShowCallback {
        void onNeedShow();
    }

    public void setOnAuthCallback(IOnAuthCallback mOnAuthCallback) {
        this.mOnAuthCallback = mOnAuthCallback;
    }

    public void setOnNeedShowCallback(IOnNeedShowCallback onNeedShowCallback) {
        mOnNeedShowCallback = onNeedShowCallback;
    }
}
