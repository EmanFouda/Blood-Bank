package com.example.bloodbank.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;










import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

    public class NewViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments=new ArrayList<>();
        List<String>  fragmentsTitle=new ArrayList<>();

        public NewViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments=new ArrayList<>();
            fragmentsTitle=new ArrayList<>();

        }
        public void addPager(Fragment fragment,String title){
            this.fragments.add(fragment);
            this.fragmentsTitle.add(title);
        }


        // Returns total number of pages
        @Override
        public int getCount() {
            return fragments.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitle.get(position);
        }
    }






