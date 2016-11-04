package com.maxm.just_stamped.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.maxm.just_stamped.js.MainActivity;
import com.maxm.just_stamped.js.R;

/**
 * Created by Mr_M on 04.11.2016.
 */

public class SignInUpWithGoogle  extends AppCompatActivity {

    TextView displayName;
    Button signerOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_google);

    }

    private void setVariablesForViews () {
        displayName = (TextView) findViewById(R.id.display_name);
        signerOut = (Button) findViewById(R.id.sign_out);
    }

    public void successSignInViewsSetter() {
        displayName.setText("Welcome, " + MainActivity.googleUserName);
        signerOut.setText("Sign OUT");
    }

    public void signOutViewsSetter() {
        displayName.setText("You are signed out");
    }
    }

