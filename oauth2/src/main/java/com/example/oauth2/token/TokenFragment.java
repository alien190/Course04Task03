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

import toothpick.Scope;
import toothpick.Toothpick;

public class TokenFragment extends Fragment {


    protected WebView mWebView;
    protected TextView mSplash;

    //todo сделать генератор
    private static final String STATE = "Ece<WIX\":6WQ!Du";

    @Inject
    protected ITokenViewModel mViewModel;

    @Inject
    CustomWebViewClient mCustomWebViewClient;

    private static final String TOKEN_KEY = "TokenKey";
    private static final String PARENT_SCOPE_NAME_KEY = "ParentScopeNameKey";
    private String mParentScopeName;
    private String mToken;

    public static TokenFragment newInstance(String token, String parentScopeName) throws Throwable {

        if (parentScopeName.isEmpty()) {
            throw new Throwable("parentScopeName cant be empty");
        }

        Bundle args = new Bundle();
        args.putString(TOKEN_KEY, token);
        args.putString(PARENT_SCOPE_NAME_KEY, parentScopeName);
        TokenFragment fragment = new TokenFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_token, container, false);

        Bundle args = getArguments();
        if (args != null) {
            mParentScopeName = args.getString(PARENT_SCOPE_NAME_KEY, "");
            mToken = args.getString(TOKEN_KEY, "");
        }

        toothpickInject();

        mViewModel.getState().observe(this, this::onChangeState);
        mViewModel.createToken(mToken);
        return view;
    }

    private void initCustomWebViewClient() {
        mCustomWebViewClient.setOnNeedShowCallback(() -> mViewModel.showAuthorizationForm());
        mCustomWebViewClient.setOnAuthCallback((code, state) -> {
            if (state.equals(STATE)) {
                mViewModel.createToken(code);
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
        initCustomWebViewClient();
        initWebView();

        mWebView.loadUrl(BuildConfig.AUTH_URL +
                BuildConfig.AUTH_PATH +
                "?scopes=" + BuildConfig.AUTH_SCOPES +
                "&client_id=" + BuildConfig.CLIENT_ID +
                "&state=" + STATE +
                "&scope=" + BuildConfig.AUTH_SCOPES);
    }

    private void toothpickInject() {
        Scope scope = Toothpick.openScopes(mParentScopeName, "Token");
        Toothpick.inject(this, scope);
    }

    private void initUI(View view) {
        mWebView = view.findViewById(R.id.webView);
        mSplash = view.findViewById(R.id.splash);
    }
}
