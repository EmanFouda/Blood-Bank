package com.example.bloodbank.ui.fragment.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;

import static com.example.bloodbank.helper.helper.ree;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {
    private final int SPLASH_DISPLAY_TIMER = 5000;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_splash, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SliderFragment sliderFragment=new SliderFragment();
                ree(sliderFragment,R.id.Activity_Splash_Fl_SplashFrame,getFragmentManager().beginTransaction());

            }
        },SPLASH_DISPLAY_TIMER);


        return view;
    }

}
