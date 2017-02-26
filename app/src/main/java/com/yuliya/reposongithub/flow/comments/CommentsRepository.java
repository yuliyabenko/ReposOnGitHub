package com.yuliya.reposongithub.flow.comments;

import android.content.Context;

import com.yuliya.reposongithub.model.Comment;

import java.util.List;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Yuliya on 22.02.2017.
 */

public class CommentsRepository implements CommentsDataSource {

    private CommentsLocalDataSource localSource = new CommentsLocalDataSource();

    private CommentsRemoteDataSource remoteSource = new CommentsRemoteDataSource();

    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        return remoteSource.getComments(user, repo)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(list -> {
                    return localSource.saveRepo(list);
                })
                .onErrorResumeNext(localSource.getComments(user, repo));
    }

    @Override
    public void clearComments() {

    }

    @Override
    public void init(Context context) {
        localSource.init(context); ;
        remoteSource.init(context);
    }
}
