
package com.calculator.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.calculator.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "NUm4Q1p2DZEseMVkmh1bhJltY";
    private static final String TWITTER_SECRET = "kzha4TpBvqxkRSNCpCeY8ku6MxpWsxoN9x7MAH6u7ZiZrzLOTk";
    private final String TAG = LoginActivity.class.getSimpleName();

    private static final int RC_SIGN_IN = 9001;
    private CallbackManager facebookCallbackManager;
    private TwitterAuthClient twitterAuthClient;
    private GoogleApiClient mGoogleApiClient;

    @Bind(R.id.login)
    EditText login;

    @Bind(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //twitter init
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        twitterAuthClient = new TwitterAuthClient();
        //
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //facebook init
        FacebookSdk.sdkInitialize(getApplicationContext());
        facebookCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(facebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.i(TAG, "facebook onSuccess: loginResult id " + loginResult.getAccessToken().getUserId());

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());

                                        // Application code
                                        try {
                                            String email = object.getString("email");
                                            String name = object.getString("name");
                                            Log.i(TAG, "onCompleted: facebook name: " + name + " email: " + email);

                                            Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
                                            LoginActivity.this.startActivity(intent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Log.i(TAG, "facebook onCancel: ");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.i(TAG, "facebook onError: ");
                    }
                });
        //google init
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @OnClick(R.id.log_in)
    void logIn() {
        Log.i(TAG, "logIn: clicked");
        if (!isValid()) {
            Log.i(TAG, "logIn: invalid");
            //Snackbar.make(coordinatorLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        } else {
            Log.i(TAG, "logIn: valid");
            Intent intent = new Intent(this, CalculatorActivity.class);
            /*intent.putExtra(CalculatorActivity.LOGIN_KEY, login.getText().toString());
            intent.putExtra(CalculatorActivity.PASSWORD_KEY, password.getText().toString());*/
            startActivity(intent);
        }
    }

    private boolean isValid() {
        //some validation logic here
        boolean isValid = true;
        if (login.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    @OnClick(R.id.log_in_fb)
    void logInFacebook() {
        Log.i(TAG, "logInFacebook: clicked");
        LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList("email"));

    }

    @OnClick(R.id.log_in_twt)
    void logInTwitter() {
        Log.i(TAG, "logInTwitter: clicked");
        twitterAuthClient.authorize(LoginActivity.this, new Callback<TwitterSession>() {
            // TODO: 18.08.16 email from twitter
            @Override
            public void success(Result<TwitterSession> result) {
                Log.i(TAG, "success:  twitter username: " + result.data.getUserName());
                Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
                LoginActivity.this.startActivity(intent);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.i(TAG, "failure: " + exception.toString());
            }
        });

    }

    @OnClick(R.id.log_in_gp)
    void logInGooglePlus() {
        Log.i(TAG, "logInGooglePlus: clicked");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.i(TAG, "handleSignInResult:  Google account email: " + acct.getEmail());
            Log.i(TAG, "handleSignInResult:  Google account name: " + acct.getDisplayName());
            Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
            LoginActivity.this.startActivity(intent);
        }
    }

    @OnClick(R.id.recovery)
    void recoverAccount() {
        Intent intent = new Intent(this, RecoverActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.registration)
    void registrate() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: requestCode: " + requestCode + " resultCode: " + resultCode);

        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);

        twitterAuthClient.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
