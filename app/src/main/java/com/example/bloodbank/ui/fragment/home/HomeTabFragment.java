package com.example.bloodbank.ui.fragment.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NewViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTabFragment extends Fragment {


    @BindView(R.id.Fragment_Home_Tab_Tl_Sliding_Tab)
    TabLayout FragmentHomeTabTlSlidingTab;
    @BindView(R.id.home_frame)
    FrameLayout homeFrame;
    @BindView(R.id.Fragment_Home_Tab_Vp_Home)
    ViewPager FragmentHomeTabVpHome;
    Unbinder unbinder;

    public HomeTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_tab, container, false);
        NewViewPagerAdapter newViewPagerAdapter = new NewViewPagerAdapter(getActivity().getSupportFragmentManager());
        newViewPagerAdapter.addPager(new ArticlesFragment(), getString(R.string.articles));
        newViewPagerAdapter.addPager(new DonationRequestsFragment(), getString(R.string.donation_request));

        unbinder = ButterKnife.bind(this, view);
        FragmentHomeTabVpHome.setAdapter(newViewPagerAdapter);
        FragmentHomeTabTlSlidingTab.setupWithViewPager(FragmentHomeTabVpHome);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
