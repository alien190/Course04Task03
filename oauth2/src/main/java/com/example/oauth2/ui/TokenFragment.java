package com.example.oauth2.ui;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


import com.example.oauth2.BuildConfig;
import com.example.oauth2.R;
import com.example.oauth2.di.TokenModule;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class TokenFragment extends Fragment {

    public WebView mWebView;
    public WebView mSplash;
    public View view;
    public TextView mTvError;

    //todo сделать генератор
    private static final String STATE = "Ece<WIX\":6WQ!Du";

    @Inject
    protected ITokenViewModel mViewModel;

    @Inject
    CustomWebViewClient mCustomWebViewClient;

    @Inject
    Context mContext;

    private static final String PARENT_SCOPE_NAME_KEY = "ParentScopeNameKey";
    private static final String DO_LOGOUT_KEY = "DoLogoutKey";

    private String mParentScopeName;
    private Boolean mDoLogout;

    public static TokenFragment newInstance(String parentScopeName, Boolean doLogout) {

//        if (parentScopeName.isEmpty()) {
//            throw new Throwable("parentScopeName cant be empty");
//        }

        Bundle args = new Bundle();
        args.putString(PARENT_SCOPE_NAME_KEY, parentScopeName);
        args.putBoolean(DO_LOGOUT_KEY, doLogout);
        TokenFragment fragment = new TokenFragment();
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fr_token, container, false);
            initUI(view);
            initSplash();
            Bundle args = getArguments();
            if (args != null) {
                mParentScopeName = args.getString(PARENT_SCOPE_NAME_KEY, "");
                mDoLogout = args.getBoolean(DO_LOGOUT_KEY, false);
            }
            toothpickInject();
            if(mDoLogout) {
                clearCookies(mContext);
                mViewModel.clearToken();
            }
            mViewModel.getState().observe(this, this::onChangeState);
            mViewModel.startNewAuth();
        }
        return view;
    }

    private void initSplash() {
        WebSettings ws = mSplash.getSettings();
        ws.setDisplayZoomControls(true);
        mSplash.loadUrl("file:///android_asset/splash.html");
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
        mCustomWebViewClient.setOnReceivedHttpError(this::onHttpError);
    }

    private void onHttpError(){
        mSplash.setVisibility(View.GONE);
        mWebView.setVisibility(View.GONE);
        mTvError.setVisibility(View.VISIBLE);
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
                showWebView();
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
        mTvError.setVisibility(View.GONE);
    }

    private void hideWebView() {
        mSplash.setVisibility(View.VISIBLE);
        mWebView.setVisibility(View.GONE);
        mTvError.setVisibility(View.GONE);
    }

    private void startAuth() {
        initCustomWebViewClient();
        initWebView();

        mWebView.loadUrl(BuildConfig.GET_CODE_URL + "&state=" + STATE);
    }

    private void toothpickInject() {
        Scope scope = Toothpick.openScopes(mParentScopeName, "Token");
        scope.installModules(new TokenModule(this));
        Toothpick.inject(this, scope);
    }

    private void initUI(View view) {
        mWebView = view.findViewById(R.id.webView);
        mSplash = view.findViewById(R.id.splashWebView);
        mTvError = view.findViewById(R.id.tvError);
    }

    @Override
    public void onDestroy() {
        Toothpick.closeScope("Token");
        super.onDestroy();
    }

    public MutableLiveData<String> getToken() {
        return mViewModel.getToken();
    }

    //todo вынести в отделный класс функционал очистки cookies
    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

}
