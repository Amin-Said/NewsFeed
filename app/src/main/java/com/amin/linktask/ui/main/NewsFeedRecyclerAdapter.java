package com.amin.linktask.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amin.linktask.R;
import com.amin.linktask.databinding.NewsFeedItemBinding;
import com.amin.linktask.pojo.Article;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NewsFeedRecyclerAdapter extends RecyclerView.Adapter<NewsFeedRecyclerAdapter.ViewHolder> {

    private ArrayList<Article> itemsList;
    private Context context;
    private Article clickedArticle;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onItemClick(clickedArticle);
        }
    };

    public interface OnItemClickListener {
        void onItemClick(Article item);
    }

    private final OnItemClickListener listener;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private NewsFeedItemBinding newsFeedItemBinding;

        public ViewHolder(@NonNull NewsFeedItemBinding newsFeedItemBinding) {
            super(newsFeedItemBinding.getRoot());
            this.newsFeedItemBinding = newsFeedItemBinding;
        }
    }

    public NewsFeedRecyclerAdapter(ArrayList<Article> list, Context context,OnItemClickListener listener) {
        this.itemsList = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public NewsFeedRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        NewsFeedItemBinding newsFeedItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.news_feed_item, parent, false);
        return new NewsFeedRecyclerAdapter.ViewHolder(newsFeedItemBinding);
    }

    @Override
    public void onBindViewHolder(NewsFeedRecyclerAdapter.ViewHolder holder, int position) {

        final Article model = itemsList.get(position);
        holder.newsFeedItemBinding.setArticle(model);
        clickedArticle = model;
        holder.newsFeedItemBinding.cardView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

}
