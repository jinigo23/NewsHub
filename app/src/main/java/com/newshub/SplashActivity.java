package com.newshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.newshub.ui_main.MainActivity;
import com.newshub.ui_register_login.BlockedActivity;
import com.newshub.ui_register_login.LoginActivity;
import com.newshub.ui_register_login.RegisterActivity;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);

        preferences = getSharedPreferences ("my_pin", MODE_PRIVATE);
        handler = new Handler ( );
        handler.postDelayed (new Runnable ( ) {
            @Override
            public void run() {
                int login_pin = preferences.getInt ("PIN", 0);
                long block = preferences.getLong ("Block_time", 0);
                int main = preferences.getInt ("Success", 0);
                String mPin = String.valueOf (login_pin);
                Log.d ("Pin", "Pin : "+ mPin + "\t" + String.valueOf (block)+"\t"+ String.valueOf (main));

                if (mPin.length ()==1) {
//                    SharedPreferences.Editor editor = preferences.edit ();
//                    editor.remove ("PIN");
//                    editor.apply ();

                    Intent intent = new Intent (SplashActivity.this, RegisterActivity.class);
                    startActivity (intent);
                    finish ( );
                } else if (block >1) {
                    Intent intent = new Intent (SplashActivity.this, BlockedActivity.class);
                    startActivity (intent);
                    finish ();
                } else if (main > 0) {
                    Intent intent = new Intent (SplashActivity.this, MainActivity.class);
                    intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity (intent);
                    finish ();
                } else {
                    Intent intent = new Intent (SplashActivity.this, LoginActivity.class);
                    startActivity (intent);
                    finish ();
                }
            }
        }, 3000);
    }
}
