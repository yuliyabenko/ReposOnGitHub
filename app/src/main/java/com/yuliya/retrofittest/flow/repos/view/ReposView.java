package com.yuliya.retrofittest.flow.repos.view;

import android.content.Context;

import com.yuliya.retrofittest.model.Repo;

import java.util.List;

/**
 * Created by Yuliya on 13.02.2017.
 */

public interface ReposView {

    Context getContext();

    void showRepos(List<Repo> repos);


}
