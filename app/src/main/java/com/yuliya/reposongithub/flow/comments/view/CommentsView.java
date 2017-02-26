package com.yuliya.reposongithub.flow.comments.view;

import android.content.Context;

import com.yuliya.reposongithub.model.Comment;

import java.util.List;

/**
 * Created by Yuliya on 22.02.2017.
 */

public interface CommentsView {

    Context getContext();

    void showComments(List<Comment> comments);

}
