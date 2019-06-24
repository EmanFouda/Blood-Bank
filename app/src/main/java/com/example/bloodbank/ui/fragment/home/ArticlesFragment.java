package com.example.bloodbank.ui.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ArticleRecyclerAdapter;
import com.example.bloodbank.data.model.general.categories.Categories;
import com.example.bloodbank.data.model.general.categories.CategoryData;
import com.example.bloodbank.data.model.posts.posts.Posts;
import com.example.bloodbank.data.model.posts.posts.PostsData;
import com.example.bloodbank.data.rest.ApiServices;
import com.example.bloodbank.helper.OnEndless;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.rest.RetrofitClient.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {


    @BindView(R.id.Fragment_Article_Spinner_Article)
    Spinner FragmentArticleSpinnerArticle;
    @BindView(R.id.Fragment_Article_Rl_Spinner)
    LinearLayout FragmentArticleRlSpinner;
    @BindView(R.id.Fragment_Article_Rv_Article)
    RecyclerView FragmentArticleRvArticle;
    Unbinder unbinder;
ArticleRecyclerAdapter adapter;
    private ApiServices apiServices;
    private List<PostsData> postsDataList=new ArrayList<>();
private List<CategoryData> categoryDataList=new ArrayList<>();

    private List<String> categoryNameList=new ArrayList<>();

    private List<Integer> categoryIdList=new ArrayList<>();
    private Integer categorySelectedItem;
    private int max;

    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        unbinder = ButterKnife.bind(this, view);
apiServices=getClient().create(ApiServices.class);
FragmentArticleRvArticle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        FragmentArticleRvArticle.setLayoutManager(linearLayoutManager);
        adapter=new ArticleRecyclerAdapter(getContext(),postsDataList);
        OnEndless onEndless=new OnEndless(linearLayoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {
if(current_page<=max){

    getPosts(current_page);
}
            }
        };
//        FragmentArticleRvArticle.addOnScrollListener(onEndless);
        FragmentArticleRvArticle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        getCategory();
        getPosts(1);


        return view;
    }

    private void getCategory() {
        apiServices.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.body().getStatus()==1){
                   categoryDataList.addAll(response.body().getData());
                   for ( int i=0;i<categoryDataList.size();i++){
                       categoryNameList.add(categoryDataList.get(i).getName());
                       categoryIdList.add(categoryDataList.get(i).getId());
                   }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                            (getActivity(), android.R.layout.simple_spinner_item, categoryNameList); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                            .simple_spinner_dropdown_item);
                FragmentArticleSpinnerArticle.setAdapter(spinnerArrayAdapter);
                FragmentArticleSpinnerArticle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        categorySelectedItem=categoryIdList.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                }
                else {
                    Toast.makeText(getContext(),"status is not equal one",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Toast.makeText(getContext(),"status is onFailure",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getPosts(int i) {
        apiServices.getPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",i).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.body().getStatus()==1){

postsDataList.addAll(response.body().getData().getData());
                    Log.e(ArticlesFragment.class.getSimpleName(),postsDataList.size()+"");

                }
                else {
                    Toast.makeText(getContext(),"status is not equal one",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(getContext(),"status posts is onFailure",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
