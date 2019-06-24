package com.example.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.data.model.posts.posts.PostsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.RecclerViewHolder> {


    private Context mContext;
    private List<PostsData> arrayList = new ArrayList<>();

    public ArticleRecyclerAdapter(Context mContext, List<PostsData> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public RecclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article, viewGroup, false);
        RecclerViewHolder articleRecyclerViewHolder = new RecclerViewHolder(view);
        return articleRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecclerViewHolder viewHolder, int i) {
        PostsData postsData=arrayList.get(i);
        viewHolder.ItemArticleTvArticleTitle.setText(postsData.getTitle());
        Glide.with(mContext)
                .load(postsData.getThumbnailFullPath())
                .into(viewHolder.ItemArticleIvArticleImage);
        Toast.makeText(mContext, postsData.getThumbnailFullPath(), Toast.LENGTH_LONG).show();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @OnClick({R.id.Item_Article_Tv_Article_Title, R.id.Item_Article_Iv_Article_Image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Item_Article_Tv_Article_Title:
                break;
            case R.id.Item_Article_Iv_Article_Image:
                break;
        }
    }

    public static class RecclerViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.Item_Article_Tv_Article_Title)
        TextView ItemArticleTvArticleTitle;
        @BindView(R.id.Item_Article_Iv_Article_Image)
        ImageView ItemArticleIvArticleImage;
        public RecclerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
