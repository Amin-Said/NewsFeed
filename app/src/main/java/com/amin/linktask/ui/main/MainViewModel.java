package com.amin.linktask.ui.main;

import android.util.Log;
import com.amin.linktask.api.ApiClient;
import com.amin.linktask.pojo.Data;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private String TAG = "MainViewModel";
    protected MutableLiveData<Data> newsFeedMutableLiveData = new MutableLiveData<>();

    public void getNewsFeed(String source,String apiKey){
        Single<Data> observable = ApiClient.getClient().getNewsFeedData(source,apiKey)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->newsFeedMutableLiveData.setValue(o),e->Log.d(TAG,"error : "+e));

    }


}