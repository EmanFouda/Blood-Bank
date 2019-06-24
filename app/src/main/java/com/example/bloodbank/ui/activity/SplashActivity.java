package com.example.bloodbank.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.fragment.splash.SplashFragment;

import static com.example.bloodbank.helper.helper.ree;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashFragment splashFragment=new SplashFragment();
        ree(splashFragment,R.id.Activity_Splash_Fl_SplashFrame,getSupportFragmentManager().beginTransaction());
    }
}
