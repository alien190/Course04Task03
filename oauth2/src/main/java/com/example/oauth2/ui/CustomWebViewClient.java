package com.example.oauth2.ui;

import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class CustomWebViewClient extends WebViewClient {

    private IOnAuthCallback mOnAuthCallback;
    private IOnNeedShowCallback mOnNeedShowCallback;
    private IonReceivedHttpError mOnReceivedHttpError;
    private boolean mIsNeedShow = false;

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
            mIsNeedShow = true;

            return false;
        } else {
            mIsNeedShow = false;
            if (mOnAuthCallback != null) {
                mOnAuthCallback.onAuthComplete(uri.getQueryParameter("code"),
                        uri.getQueryParameter("state"));
            }
            return true;
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (mOnReceivedHttpError != null) {
            mOnReceivedHttpError.onError();
        }
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        if (mOnReceivedHttpError != null) {
            mOnReceivedHttpError.onError();
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (mOnReceivedHttpError != null) {
            mOnReceivedHttpError.onError();
        }
    }

    interface IOnAuthCallback {
        void onAuthComplete(String code, String state);
    }

    interface IOnNeedShowCallback {
        void onNeedShow();
    }

    interface IonReceivedHttpError {
        void onError();
    }


    public void setOnAuthCallback(IOnAuthCallback mOnAuthCallback) {
        this.mOnAuthCallback = mOnAuthCallback;
    }

    public void setOnNeedShowCallback(IOnNeedShowCallback onNeedShowCallback) {
        mOnNeedShowCallback = onNeedShowCallback;
    }

    public void setOnReceivedHttpError(IonReceivedHttpError onReceivedHttpError) {
        mOnReceivedHttpError = onReceivedHttpError;
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        if (mOnNeedShowCallback != null && mIsNeedShow) {
            mOnNeedShowCallback.onNeedShow();
        }
    }
}
