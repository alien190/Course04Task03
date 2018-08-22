package com.example.oauth2.token;

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


import com.example.oauth2.BuildConfig;
import com.example.oauth2.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;

public class TokenFragment extends Fragment {

    @BindView(R.id.webView)
    protected WebView mWebView;
    @BindView(R.id.splash)
    protected TextView mSplash;

    //todo сделать генератор
    private static final String STATE = "Ece<WIX\":6WQ!Du";

    @Inject
    protected ITokenViewModel mViewModel;

    @Inject
    CustomWebViewClient mCustomWebViewClient;

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
        ButterKnife.bind(this, view);
        toothpickInject();
        initCustomWebViewClient();
        initWebView();
        mViewModel.getState().observe(this, this::onChangeState);
        return view;
    }

    private void initCustomWebViewClient() {
        mCustomWebViewClient.setOnNeedShowCallback(() -> mViewModel.showAuthorizationForm());
        mCustomWebViewClient.setOnAuthCallback((code, state) -> {
            if (state.equals(STATE)) {
                mViewModel.createToken(code, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET);
            } else {
                //todo реализовать обработку ошибки
            }
        });
    }

    private void initWebView() {
        WebSettings webViewSettings = mWebView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(mCustomWebViewClient);
    }

    private void onChangeState(Integer state) {
        switch (state) {
            case ITokenViewModel.STATE_AUTH: {
                startAuth();
                //showWebView();
                break;
            }
            case ITokenViewModel.STATE_AUTH_INTERACTIVE: {
                startAuth();
                showWebView();
                break;
            }

            case ITokenViewModel.STATE_SPLASH: {
                hideWebView();
                break;
            }
            case ITokenViewModel.STATE_SHOW_AUTH: {
                showWebView();
                break;
            }
        }
    }

    private void showWebView() {
        mSplash.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
    }

    private void hideWebView() {
        mSplash.setVisibility(View.VISIBLE);
        mWebView.setVisibility(View.GONE);
    }

    private void startAuth() {
        mWebView.loadUrl(BuildConfig.AUTH_URL +
                BuildConfig.AUTH_PATH +
                "?scopes=" + BuildConfig.AUTH_SCOPES +
                "&client_id=" + BuildConfig.CLIENT_ID +
                "&state=" + STATE +
                "&scope=" + BuildConfig.AUTH_SCOPES);
    }

    private void toothpickInject(){
        Scope scope = Toothpick.openScopes("Application", "Token");
        Toothpick.inject(this, scope);
    }
}
