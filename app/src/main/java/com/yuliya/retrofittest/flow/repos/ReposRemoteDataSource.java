package com.yuliya.retrofittest.flow.repos;

import android.content.Context;

import com.yuliya.retrofittest.base.BaseDateSource;
import com.yuliya.retrofittest.base.BaseRemoteDataSource;
import com.yuliya.retrofittest.model.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 08.02.2017.
 */

public class ReposRemoteDataSource extends BaseRemoteDataSource
        implements ReposDataSource {

    @Override
    public Single<List<Repo>> getRepos(String user) {
       return reposeService.getRepos(user);
    }

    @Override
    public void clearRepos() {

    }


}
