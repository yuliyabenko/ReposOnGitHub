package com.yuliya.retrofittest.flow.repos;

import com.yuliya.retrofittest.base.BaseDateSource;
import com.yuliya.retrofittest.model.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 08.02.2017.
 */

public interface ReposDataSource extends BaseDateSource {

    Single<List<Repo>> getRepos(String user);
    void clearRepos();
}
