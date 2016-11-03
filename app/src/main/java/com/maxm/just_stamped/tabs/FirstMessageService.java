package com.maxm.just_stamped.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.maxm.just_stamped.js.R;

/**
 * Created by Mr_M on 03.11.2016.
 */

public class FirstMessageService extends AppCompatActivity {

    ImageButton btnGoogleAuthorization;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_first_message);
        setButtonGoogle();
    }

    /*
    Этот метод задает параметры слушателя кнопки для google_authorization и связывает ее с btnGoogleAuthorization
    */
    protected void setButtonGoogle () {
        btnGoogleAuthorization = (ImageButton) findViewById(R.id.google_authorization);
        btnGoogleAuthorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
