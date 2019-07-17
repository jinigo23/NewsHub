package com.newshub.ui_register_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.newshub.R;

public class BlockedActivity extends AppCompatActivity {

    private Button btnOk;
    Handler handler;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_blocked);

        preferences = getSharedPreferences ("my_pin", MODE_PRIVATE);

        handler = new Handler ();
        handler.postDelayed (new Runnable ( ) {
            @Override
            public void run() {
                SharedPreferences.Editor editor = preferences.edit ();
                editor.remove ("Block_time");
                editor.commit ();

                Intent intent = new Intent (BlockedActivity.this, LoginActivity.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity (intent);
                finish ();
            }
        }, 60000);
        btnOk = (Button)findViewById (R.id.btnOk);
        btnOk.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });
    }
}
