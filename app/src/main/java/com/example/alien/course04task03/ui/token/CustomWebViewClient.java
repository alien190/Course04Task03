package com.example.alien.course04task03.ui.token;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class CustomWebViewClient extends WebViewClient {

    private IOnAuthCallback mOnAuthCallback;

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
            return false;
        } else {
            if (mOnAuthCallback != null) {

                mOnAuthCallback.onAuthComplete(request.getUrl().getQueryParameter("code"),
                        request.getUrl().getQueryParameter("state"));
            }
//            Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
//            view.getContext().startActivity(intent);
            Timber.d("host:%s", request.getUrl().getHost());
            Timber.d("path:%s", request.getUrl().getPath());

            return true;
        }

    }

    interface IOnAuthCallback {
        void onAuthComplete(String code, String state);
    }

    public void setOnAuthCallback(IOnAuthCallback mOnAuthCallback) {
        this.mOnAuthCallback = mOnAuthCallback;
    }
}
