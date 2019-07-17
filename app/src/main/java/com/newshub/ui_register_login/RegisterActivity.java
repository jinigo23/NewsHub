package com.newshub.ui_register_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.newshub.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String USER_DETAILS = "user_details";
    private EditText email, phone;
    private Button btnNext;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

        email = (EditText) findViewById (R.id.email_ID);
        phone = (EditText) findViewById (R.id.phone);
        btnNext = (Button) findViewById (R.id.btnNext);

        preferences = getSharedPreferences (USER_DETAILS, MODE_PRIVATE);

        btnNext.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                String regEmail = email.getText ( ).toString ( ).trim ( );
                String regPhone = phone.getText ( ).toString ( ).trim ( );

                SharedPreferences.Editor editor = preferences.edit ( );
                if (regEmail.length ( ) != 0) {
                    editor.putString ("Email", regEmail);
                    editor.putString ("Phone", regPhone);
                    editor.commit ( );

                    Intent intent = new Intent (RegisterActivity.this, CreatePinActivity.class);
                    startActivity (intent);
                    finish ( );

                } else {
                    email.setError ("Email ID is required");
                }
            }
        });
    }
}
