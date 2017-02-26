package com.yuliya.reposongithub.flow.repos;

import com.yuliya.reposongithub.base.BaseDateSource;
import com.yuliya.reposongithub.model.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 08.02.2017.
 */

public interface ReposDataSource extends BaseDateSource {

    Single<List<Repo>> getRepos(String user);

    void clearRepos();

}
