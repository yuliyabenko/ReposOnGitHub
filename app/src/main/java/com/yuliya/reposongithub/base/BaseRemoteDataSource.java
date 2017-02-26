package com.yuliya.reposongithub.base;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Yuliya on 08.02.2017.
 */

public abstract class BaseRemoteDataSource implements BaseDateSource{

    public ReposService reposService;

    protected CommentsService commentsService;

    private final String NEWS_ENDPOINT = "https://api.github.com/";

    @Override
    public void init(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NEWS_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();

        reposService = retrofit.create(ReposService.class);
        commentsService = retrofit.create(CommentsService.class);
    }
}
