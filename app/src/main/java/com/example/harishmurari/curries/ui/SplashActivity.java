package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.harishmurari.curries.R;

/**
 * Created by hmurarishetty on 9/24/17.
 */

public class SplashActivity extends AppCompatActivity {

    private static int Splash_Time_Out = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, Splash_Time_Out);

    }
}
