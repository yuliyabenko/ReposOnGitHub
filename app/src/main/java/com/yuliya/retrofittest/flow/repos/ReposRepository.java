package com.yuliya.retrofittest.flow.repos;

import android.content.Context;

import com.yuliya.retrofittest.base.BaseLocalDataSource;
import com.yuliya.retrofittest.flow.repos.ReposDataSource;
import com.yuliya.retrofittest.flow.repos.ReposLocalDataSource;
import com.yuliya.retrofittest.flow.repos.ReposRemoteDataSource;
import com.yuliya.retrofittest.model.Repo;

import java.util.List;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Yuliya on 13.02.2017.
 */

public class ReposRepository implements ReposDataSource {

    private ReposLocalDataSource localSource = new ReposLocalDataSource();

    private ReposRemoteDataSource remoteSource = new ReposRemoteDataSource();

    @Override
    public Single<List<Repo>> getRepos(String user) {
        return remoteSource.getRepos(user)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(list -> {
                    return localSource.saveRepo(list);
                })
                .onErrorResumeNext(localSource.getRepos(user));
    }



    @Override
    public void clearRepos() {
        localSource.clearRepos();
    }

    @Override
    public void init(Context context) {
        localSource.init(context); ;
        remoteSource.init(context);
    }
}
