package com.maxm.just_stamped.authorization;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class BarHeader extends AppCompatActivity {

    TextView userName;
    TextView userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar_header);
    }

    /*
    Этот метод связывает все глобальные переменные View с элементами View на Activity
     */

    }

