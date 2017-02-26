package com.yuliya.reposongithub.flow.comments;

import com.yuliya.reposongithub.base.BaseRemoteDataSource;
import com.yuliya.reposongithub.model.Comment;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 22.02.2017.
 */

public class CommentsRemoteDataSource extends BaseRemoteDataSource
        implements CommentsDataSource {

    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        return commentsService.getComments(user, repo);
    }

    @Override
    public void clearComments() {

    }
}
