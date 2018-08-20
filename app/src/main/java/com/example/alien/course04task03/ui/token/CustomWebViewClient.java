package com.example.alien.course04task03.ui.token;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class CustomWebViewClient extends WebViewClient {

    private IOnAuthCallback mOnAuthCallback;
    private IOnNeedShowCallback mOnNeedShowCallback;

    List<String> allowedPath = new ArrayList<String>() {{
        add("/login/oauth/authorize");
        add("/login");
        add("/password_reset");
        add("/join");
        add("/explore");
    }};

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        if (request.getUrl().getHost().equals("github.com")
                && allowedPath.contains(request.getUrl().getPath())) {

            if (mOnNeedShowCallback != null) {
                mOnNeedShowCallback.onNeedShow();
            }
            return false;
        } else {
            if (mOnAuthCallback != null) {
                mOnAuthCallback.onAuthComplete(request.getUrl().getQueryParameter("code"),
                        request.getUrl().getQueryParameter("state"));
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
