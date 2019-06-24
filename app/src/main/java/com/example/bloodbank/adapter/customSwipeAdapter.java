package com.example.bloodbank.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bloodbank.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by efouda on 1/31/19.
 */

public class customSwipeAdapter extends PagerAdapter {

    @BindView(R.id.swipe_item_im_slider_image_view)
    ImageView swipeItemImSliderImageView;


    private int[] imageResources = {R.drawable.ic_slider1, R.drawable.ic_slider2, R.drawable.ic_slider1};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public customSwipeAdapter(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.swipe_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.swipe_item_im_slider_image_view);
        imageView.setImageResource(imageResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
//        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }


}
