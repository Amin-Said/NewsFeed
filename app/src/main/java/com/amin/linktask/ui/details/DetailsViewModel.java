package com.amin.linktask.ui.details;

import com.amin.linktask.pojo.Article;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {
    String TAG = "DetailsViewModel";
    MutableLiveData<Article> articleMutableLiveData = new MutableLiveData<>();

    public void sendData(Article article) {
        articleMutableLiveData.setValue(article);
    }

    public LiveData<Article> getArticle() {
        return articleMutableLiveData;

    }
}
