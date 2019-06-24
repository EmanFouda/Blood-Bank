package com.example.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bloodbank.ui.fragment.home.ArticlesFragment;
import com.example.bloodbank.ui.fragment.home.DonationRequestsFragment;

public class HomeSwipeAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public HomeSwipeAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0){
return new ArticlesFragment();
        }
        else if (i==1){
return new DonationRequestsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Articlesِ" ;
            case 1:
                return "Donation Request";
                default:
                    return null;
        }
    }
}
