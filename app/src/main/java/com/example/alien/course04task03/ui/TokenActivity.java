package com.example.alien.course04task03.ui;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.alien.course04task03.R;
import com.example.alien.course04task03.di.tokenActivity.TokenActivityModule;
import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;

public class TokenActivity extends AppCompatActivity {


    /*CONSTANT FOR THE AUTHORIZATION PROCESS*/

    /****FILL THIS WITH YOUR INFORMATION*********/
    //This is the public api key of our application
    private static final String API_KEY = "77samz4zvzm0w8";
    //This is the private api key of our application
    private static final String SECRET_KEY = "0922fc147a905a77cf6b55e2be2a82ad395791e9";
    //This is any string we want to use. This will be used for avoid CSRF attacks. You can generate one here: http://strongpasswordgenerator.com/
    private static final String STATE = "Ece<WIX\":6WQ!Du";
    //This is the url that LinkedIn Auth process will redirect to. We can put whatever we want that starts with http:// or https:// .
    //We use a made up url that we will intercept when redirecting. Avoid Uppercases.
    private static final String REDIRECT_URI = "https://www.google.com";
    /*********************************************/

    //These are constants used for build the urls
    private static final String AUTHORIZATION_URL = "https://api.github.com/authorization";
    private static final String ACCESS_TOKEN_URL = "https://www.linkedin.com/uas/oauth2/accessToken";
    private static final String SECRET_KEY_PARAM = "client_secret";
    private static final String RESPONSE_TYPE_PARAM = "response_type";
    private static final String GRANT_TYPE_PARAM = "grant_type";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String RESPONSE_TYPE_VALUE = "code";
    private static final String CLIENT_ID_PARAM = "client_id";
    private static final String STATE_PARAM = "state";
    private static final String REDIRECT_URI_PARAM = "redirect_uri";
    /*---------------------------------------*/
    private static final String QUESTION_MARK = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUALS = "=";

    @BindView(R.id.webView)
    protected WebView mWebView;

    @Inject
    protected ITokenViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);

        Scope scope = Toothpick.openScopes("Application", this.getClass().getSimpleName());
        scope.installModules(new TokenActivityModule(this));
        Toothpick.inject(this, scope);

        mViewModel.getState();
        //  Timber.d("Token initial value: %d", mITokenValidator.getTokenState().getValue());
        //  mITokenValidator.getTokenState().observe(this, integer -> Timber.d("Token change: %d", integer));


//        mWebView = findViewById(R.id.webView);
//
//        WebSettings webViewSettings = mWebView.getSettings();
//        webViewSettings.setJavaScriptEnabled(true);
//
//        CustomWebViewClient customWebViewClient = new CustomWebViewClient();
//        customWebViewClient.setOnAuthCallback(new CustomWebViewClient.IOnAuthCallback() {
//            @Override
//            public void onAuthComplete(String code, String state) {
//                if (state.equals(STATE))
//                    Toast.makeText(TokenActivity.this, code, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mWebView.setWebViewClient(customWebViewClient);
//
//        mWebView.loadUrl("https://github.com/login/oauth/authorize?scopes=user,repo&client_id=b53de5616b92c534190b&state=" + STATE);

    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this.getClass().getSimpleName());
        super.onDestroy();
    }

    /**
     * Method that generates the url for get the access token from the Service
     *
     * @return Url
     */
    private static String getAccessTokenUrl(String authorizationToken) {
        return ACCESS_TOKEN_URL
                + QUESTION_MARK
                + GRANT_TYPE_PARAM + EQUALS + GRANT_TYPE
                + AMPERSAND
                + RESPONSE_TYPE_VALUE + EQUALS + authorizationToken
                + AMPERSAND
                + CLIENT_ID_PARAM + EQUALS + API_KEY
                + AMPERSAND
                + REDIRECT_URI_PARAM + EQUALS + REDIRECT_URI
                + AMPERSAND
                + SECRET_KEY_PARAM + EQUALS + SECRET_KEY;
    }

    /**
     * Method that generates the url for get the authorization token from the Service
     *
     * @return Url
     */


    private static String getAuthorizationUrl() {
        return AUTHORIZATION_URL
                + QUESTION_MARK + RESPONSE_TYPE_PARAM + EQUALS + RESPONSE_TYPE_VALUE
                + AMPERSAND + CLIENT_ID_PARAM + EQUALS + API_KEY
                + AMPERSAND + STATE_PARAM + EQUALS + STATE
                + AMPERSAND + REDIRECT_URI_PARAM + EQUALS + REDIRECT_URI;
    }
}



