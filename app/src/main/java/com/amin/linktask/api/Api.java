package com.amin.linktask.api;


import com.amin.linktask.pojo.Data;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("articles/")
    public Single<Data> getNewsFeedData(@Query("source") String source, @Query("apiKey") String apiKey);
}
