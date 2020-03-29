package com.amin.linktask.api;

import com.amin.linktask.pojo.Data;
import com.amin.linktask.utils.Constants;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Api newsFeedApi;

    private static ApiClient client;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        newsFeedApi = retrofit.create(Api.class);

    }

    public static ApiClient getClient() {
        if (client == null) {
            client = new ApiClient();
        }
        return client;
    }

    public Single<Data> getNewsFeedData(String source, String apiKey) {
        return newsFeedApi.getNewsFeedData(source, apiKey);
    }

}