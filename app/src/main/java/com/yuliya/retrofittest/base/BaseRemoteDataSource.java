package com.yuliya.retrofittest.base;

import android.content.Context;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;
import rx.schedulers.Schedulers;

/**
 * Created by Yuliya on 08.02.2017.
 */

public abstract class BaseRemoteDataSource implements BaseDateSource{

    protected ReposeService reposeService;

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

        reposeService = retrofit.create(ReposeService.class);
    }
}
