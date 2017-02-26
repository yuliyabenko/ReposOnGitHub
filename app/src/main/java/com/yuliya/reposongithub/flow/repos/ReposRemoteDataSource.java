package com.yuliya.reposongithub.flow.repos;

import com.yuliya.reposongithub.base.BaseRemoteDataSource;
import com.yuliya.reposongithub.model.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 08.02.2017.
 */

public class ReposRemoteDataSource extends BaseRemoteDataSource
        implements ReposDataSource {

    @Override
    public Single<List<Repo>> getRepos(String user) {
       return reposService.getRepos(user);
    }

    @Override
    public void clearRepos() {

    }


}
