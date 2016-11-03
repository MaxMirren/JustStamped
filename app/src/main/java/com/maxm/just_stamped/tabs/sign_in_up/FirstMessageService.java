package com.maxm.just_stamped.tabs.sign_in_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.maxm.just_stamped.js.R;

/**
 * Created by Mr_M on 03.11.2016.
 */

public class FirstMessageService extends AppCompatActivity {

    Button btnGoogleAuthorization;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_first_message);
    }


}
