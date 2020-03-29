package com.amin.linktask.ui.details;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amin.linktask.R;
import com.amin.linktask.databinding.FragmentDetailsBinding;
import com.amin.linktask.pojo.Article;


public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding fragmentDetailsBinding;
    private DetailsViewModel detailsViewModel;

    private Observer<Article> articleObserver = new Observer<Article>() {
        @Override
        public void onChanged(Article article) {
            fragmentDetailsBinding.setArticle(article);
        }
    };


    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);
        View view = fragmentDetailsBinding.getRoot();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        ViewModelProviders.of(getActivity()).get(DetailsViewModel.class).getArticle().observe(this,
               articleObserver);
    }

}
