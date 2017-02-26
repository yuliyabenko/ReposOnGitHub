package com.yuliya.reposongithub.flow.comments;

import com.yuliya.reposongithub.base.BaseDateSource;
import com.yuliya.reposongithub.model.Comment;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 22.02.2017.
 */

public interface CommentsDataSource extends BaseDateSource {

    Single<List<Comment>> getComments(String user, String repo);

    void clearComments();

}
