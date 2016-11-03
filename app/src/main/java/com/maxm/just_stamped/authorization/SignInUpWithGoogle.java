package com.maxm.just_stamped.authorization;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.maxm.just_stamped.js.R;

/**
 * Created by Mr_M on 04.11.2016.
 */

public class SignInUpWithGoogle  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_google);
    }
}
