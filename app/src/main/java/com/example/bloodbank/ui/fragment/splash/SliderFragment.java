package com.example.bloodbank.ui.fragment.splash;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.customSwipeAdapter;
import com.example.bloodbank.ui.activity.LogInActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.support.v4.content.ContextCompat.getDrawable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SliderFragment extends Fragment {
    private final static int NUM_PAGES = 3;
    @BindView(R.id.dots)
    LinearLayout dots;
    private ViewPager mViewPager;
    private ImageView[] dot;

    @BindView(R.id.fragment_slider_vp_slider_view_pager)
    ViewPager fragmentSliderVpSliderViewPager;
    Unbinder unbinder1;
    @BindView(R.id.fragment_slider_btn_skip)
    Button fragmentSliderBtnSkip;

    public SliderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        unbinder1 = ButterKnife.bind(this, view);
        customSwipeAdapter adapter = new customSwipeAdapter(getActivity());
        fragmentSliderVpSliderViewPager.setAdapter(adapter);
        addDots(0);
        fragmentSliderVpSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                addDots(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick(R.id.fragment_slider_btn_skip)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), LogInActivity.class);
        startActivity(intent);
    }


    public void addDots(int currentPostion) {
        if (dots != null) {
            dots.removeAllViews();
            dot = new ImageView[NUM_PAGES];

            for (int i = 0; i < NUM_PAGES; i++) {
                dot[i] = new ImageView(getActivity());
                if (i == currentPostion) {
                    dot[i].setImageDrawable(getDrawable(getActivity(), R.drawable.pager_dot_selected));
                } else {
                    dot[i].setImageDrawable(getDrawable(getActivity(), R.drawable.pager_dot_not_selected));


                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(4, 0, 4, 0);


                dots.addView(dot[i], params);
            }

        }




    }






//another method for dots



//    private void createDots(int currentPosition){
//        if (dots!=null){
//            dots.removeAllViews();
//            dot=new ImageView[NUM_PAGES];
//            for (int x=0;x<NUM_PAGES;x++){
//                dot[x]=new ImageView(getActivity());
//                if (x==currentPosition){
//                    dot[x].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.pager_dot_selected));
//                }
//                else {
//                    dot[x].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.pager_dot_not_selected));
//
//                }
//
//
//                LinearLayout.LayoutParams params=new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.setMargins(4,0,4,0);
//                dots.addView(dot[x],params);
//            }
//        }
//
//    }
}