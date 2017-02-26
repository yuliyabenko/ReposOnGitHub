package com.yuliya.reposongithub.base;

import com.yuliya.reposongithub.model.Comment;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by Yuliya on 21.02.2017.
 */

public interface CommentsService {

    @GET("/repos/{user}/{repo}/issues/comments")
    Single<List<Comment>> getComments(@Path("user") String user,
                                      @Path("repo") String repo);

}
