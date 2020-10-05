package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class AuthActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 3216;
    private static final String GOOGLE_AUTH = "GoogleAuth";
    private final String serverClientId =
            "133485639209-rtticmhujqkfis1irn48jp2crl5tqnf5.apps.googleusercontent.com";
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initToolbar();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(serverClientId)
                .requestServerAuthCode(serverClientId, false)
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(view -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.authentication);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        return toolbar;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String token = account.getIdToken();
            Log.w(GOOGLE_AUTH, "signInResult:success code=" + token);

            if (!TextUtils.isEmpty(token)) {
                Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
            }
        } catch (ApiException e) {
            Log.w(GOOGLE_AUTH, "signInResult:failed code=" + e.getStatusCode());
        }
    }


}