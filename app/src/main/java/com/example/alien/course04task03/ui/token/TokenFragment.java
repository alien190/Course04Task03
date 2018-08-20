package com.example.alien.course04task03.ui.token;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.R;

import javax.inject.Inject;

public class TokenFragment extends Fragment {

    private WebView mWebView;
    private TextView mSplash;

    //todo сделать генератор
    private static final String STATE = "Ece<WIX\":6WQ!Du";

    @Inject
    protected ITokenViewModel mViewModel;

    public static TokenFragment newInstance() {

        Bundle args = new Bundle();

        TokenFragment fragment = new TokenFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_token, container, false);
        mWebView = view.findViewById(R.id.webView);
        initWebView();
        mSplash = view.findViewById(R.id.splash);
        mViewModel.getState().observe(this, this::onChangeState);
        return view;

    }


    private void initWebView() {
        WebSettings webViewSettings = mWebView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);

        CustomWebViewClient customWebViewClient = new CustomWebViewClient();
        customWebViewClient.setOnNeedShowCallback(() -> mViewModel.showAuthorizationForm());
        customWebViewClient.setOnAuthCallback((code, state) -> {
            if (state.equals(STATE)) {
                mViewModel.createToken(code, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET);
            } else {
                //todo реализовать обработку ошибки
            }
        });
        mWebView.setWebViewClient(customWebViewClient);
    }

    private void onChangeState(Integer state) {
        switch (state) {
            case ITokenViewModel.STATE_AUTH: {
                mWebView.loadUrl("https://github.com/login/oauth/authorize?scopes=user,repo&client_id=" + BuildConfig.CLIENT_ID + "&state=" + STATE);
                break;
            }
            case ITokenViewModel.STATE_SHOW_AUTH: {
                mSplash.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
                break;
            }
        }
    }
}
