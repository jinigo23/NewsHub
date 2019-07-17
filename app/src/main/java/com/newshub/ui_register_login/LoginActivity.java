package com.newshub.ui_register_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newshub.ui_main.MainActivity;
import com.newshub.R;

public class LoginActivity extends AppCompatActivity {

    private EditText loginPin, loginPin1, loginPin2, loginPin3;
    private Button btnLogin;
    SharedPreferences preferences;
    private TextView attempts;
    int counter = 5;
    int successFul = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        loginPin = (EditText)findViewById (R.id.login_PIN);
        loginPin1 = (EditText)findViewById (R.id.login_PIN1);
        loginPin2 = (EditText)findViewById (R.id.login_PIN2);
        loginPin3 = (EditText)findViewById (R.id.login_PIN3);
        attempts = (TextView)findViewById (R.id.attempts);
        btnLogin = (Button)findViewById (R.id.btnLogin);

        loginPin.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (loginPin.getText ().toString ().length ()==1) {
                    loginPin1.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        loginPin1.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (loginPin1.getText ().toString ().length ()==1) {
                    loginPin2.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        loginPin2.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (loginPin2.getText ().toString ().length ()==1) {
                    loginPin3.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        preferences = getApplication ().getSharedPreferences ("my_pin", MODE_PRIVATE);

        btnLogin.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                int pin = preferences.getInt ("PIN", 0);
                String mPin = String.valueOf (pin);
                String enteredPin = loginPin.getText ().toString ()+loginPin1.getText ().toString ()+loginPin2.getText ().toString ()+loginPin3.getText ().toString ();

                if (mPin.equals (enteredPin)) {
                    successFul++;
                    SharedPreferences.Editor editor = preferences.edit ();
                    editor.putInt ("Success", successFul);
                    editor.commit ();
                    Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                    startActivity (intent);
                    finish ();
                } else {
                    Toast.makeText (getApplicationContext (), "Wrong PIN", Toast.LENGTH_SHORT).show ();
                    attempts.setVisibility (View.VISIBLE);
                    counter--;
                    if (counter>0) {
                        attempts.setText (counter+" : Attempts left");
                    } else {
                        SharedPreferences.Editor editor = preferences.edit ();
                        editor.putLong ("Block_time", System.currentTimeMillis ());
                        editor.commit ();

                        attempts.setText ("Account blocked");
                        Intent intent = new Intent (LoginActivity.this, BlockedActivity.class);
                        startActivity (intent);
                        finish ();
                    }
                }
            }
        });
    }
}
