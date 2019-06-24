package com.example.bloodbank.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.fragment.logIn.LoginOrRegisterFragment;

import static com.example.bloodbank.helper.helper.ree;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        LoginOrRegisterFragment loginOrRegisterFragment=new LoginOrRegisterFragment();
        ree(loginOrRegisterFragment,R.id.activity_log_in_fl_log_in_framework,getSupportFragmentManager().beginTransaction());

    }
}
