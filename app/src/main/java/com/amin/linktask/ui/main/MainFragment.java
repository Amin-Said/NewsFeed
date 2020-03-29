package com.amin.linktask.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amin.linktask.R;
import com.amin.linktask.databinding.FragmentMainBinding;
import com.amin.linktask.pojo.Article;
import com.amin.linktask.pojo.Data;
import com.amin.linktask.ui.details.DetailsActivity;
import com.amin.linktask.utils.Constants;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private String TAG = "MainFragment";
    private FragmentMainBinding fragmentMainBinding;
    private MainViewModel mainViewModel;
    private NewsFeedRecyclerAdapter mArticlesAdapter;

    private NewsFeedRecyclerAdapter.OnItemClickListener onArticleItemClickListener =
            new NewsFeedRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Article item) {
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra(Constants.ARTICLE,item);
                    startActivity(intent);
                }
            };

    private Observer<Data> articleObserver = new Observer<Data>() {
        @Override
        public void onChanged(Data newsFeedData) {
            if (newsFeedData!=null){
                if (newsFeedData.getArticles()!=null && !newsFeedData.getArticles().isEmpty()){

                    fragmentMainBinding.light.off();
                    fragmentMainBinding.light.setVisibility(View.GONE);

                    mArticlesAdapter = new NewsFeedRecyclerAdapter((ArrayList<Article>) newsFeedData.getArticles(),
                            getContext(),onArticleItemClickListener);
                    fragmentMainBinding.RVNewsFeed.setLayoutManager(new LinearLayoutManager(getContext()));
                    fragmentMainBinding.RVNewsFeed.setAdapter(mArticlesAdapter);
                }
            }

        }
    };

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        View view = fragmentMainBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  for loading progress animation
        if (!fragmentMainBinding.light.isOn()) {
            fragmentMainBinding.light.on();
        }

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // observer for articles
        mainViewModel.newsFeedMutableLiveData.observe(this, articleObserver);

        // request news feed data
        mainViewModel.getNewsFeed("the-next-web", "6395aec625ca4e93b289d6da05810197");
    }
}
