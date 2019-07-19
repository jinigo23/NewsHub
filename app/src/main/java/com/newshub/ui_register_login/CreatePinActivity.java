package com.newshub.ui_register_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.newshub.R;

public class CreatePinActivity extends AppCompatActivity {

    private static final String MY_PIN = "my_pin";
    private EditText pin, pin1, pin2, pin3, confirm_pin, confirm_pin1, confirm_pin2, confirm_pin3;
    private Button btnSave;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_pin);

        pin = (EditText)findViewById (R.id.login_PIN);
        pin1 = (EditText)findViewById (R.id.login_PIN1);
        pin2 = (EditText)findViewById (R.id.login_PIN2);
        pin3 = (EditText)findViewById (R.id.login_PIN3);
        confirm_pin = (EditText)findViewById (R.id.clogin_PIN);
        confirm_pin1 = (EditText)findViewById (R.id.clogin_PIN1);
        confirm_pin2 = (EditText)findViewById (R.id.clogin_PIN2);
        confirm_pin3 = (EditText)findViewById (R.id.clogin_PIN3);

        pin.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pin.getText ().toString ().length ()==1) {
                    pin1.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pin1.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pin1.getText ().toString ().length ()==1) {
                    pin2.requestFocus ();
                }else if (pin1.getText ().toString ().length ()==0){
                    pin.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pin2.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pin2.getText ().toString ().length ()==1) {
                    pin3.requestFocus ();
                }else if (pin2.getText ().toString ().length ()==0){
                    pin1.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin3.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pin3.getText ().toString ().length ()==0){
                    pin2.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirm_pin.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (confirm_pin.getText ().toString ().length ()==1) {
                    confirm_pin1.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirm_pin1.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (confirm_pin1.getText ().toString ().length ()==1) {
                    confirm_pin2.requestFocus ();
                }else if (confirm_pin1.getText ().toString ().length ()==0){
                    confirm_pin.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirm_pin2.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (confirm_pin2.getText ().toString ().length ()==1) {
                    confirm_pin3.requestFocus ();
                }else if (confirm_pin2.getText ().toString ().length ()==0){
                    confirm_pin1.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirm_pin3.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (confirm_pin3.getText ().toString ().length ()==0){
                    confirm_pin2.requestFocus ();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnSave = (Button)findViewById (R.id.btnSave);

        preferences = getSharedPreferences (MY_PIN, MODE_PRIVATE);

        btnSave.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                String myPin = pin.getText ().toString ()+pin1.getText ().toString ()+pin2.getText ().toString ()+pin3.getText ().toString ();
                String confirmPin = confirm_pin.getText ().toString ()+confirm_pin1.getText ().toString ()+confirm_pin2.getText ().toString ()+confirm_pin3.getText ().toString ();

                SharedPreferences.Editor editor = preferences.edit ();
                if (myPin.length ()==4 && myPin.equals (confirmPin)) {
                    editor.putInt ("PIN", Integer.valueOf (myPin));
                    editor.commit ();

                    Intent intent = new Intent (CreatePinActivity.this, LoginActivity.class);
                    Snackbar.make (findViewById (android.R.id.content), "PIN created successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                    startActivity (intent);
                    finish ();

                } else {
                    Snackbar.make (findViewById (android.R.id.content), "Entered PIN not match", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
//                    Toast.makeText (getApplicationContext (), "Entered PIN not match", Toast.LENGTH_SHORT).show ();
                }
            }
        });
    }


}
