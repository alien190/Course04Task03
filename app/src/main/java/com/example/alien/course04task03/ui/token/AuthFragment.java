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

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.R;

import javax.inject.Inject;

import okhttp3.Interceptor;

public class AuthFragment extends Fragment {

    private WebView mWebView;

    //todo сделать генератор
    private static final String STATE = "Ece<WIX\":6WQ!Du";

    @Inject
    protected ITokenViewModel mViewModel;

    public static AuthFragment newInstance() {

        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auth, container, false);
        mWebView = view.findViewById(R.id.webView);

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

        mViewModel.getState().observe(this, this::onChangeState);
        return view;
    }


    private void onChangeState(Integer state) {
        if(state == ITokenViewModel.STATE_AUTH) {
            mWebView.loadUrl("https://github.com/login/oauth/authorize?scopes=user,repo&client_id="+ BuildConfig.CLIENT_ID +"&state=" + STATE);
        }
    }

}
