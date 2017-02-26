package com.yuliya.reposongithub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.yuliya.reposongithub.flow.comments.view.CommentAdapter;
import com.yuliya.reposongithub.flow.comments.view.CommentsPresenter;
import com.yuliya.reposongithub.flow.comments.view.CommentsView;
import com.yuliya.reposongithub.model.Comment;
import com.yuliya.retrofittest.R;

import java.util.List;

public class CommentsActivity extends AppCompatActivity
                            implements CommentsView {

    private RecyclerView mRecyclerView;

    private Toolbar mToolbar;

    private CommentsPresenter commentsPresenter = new CommentsPresenter();

    private String user;

    private String repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        commentsPresenter.onAttach(this);
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        repo = intent.getStringExtra("repo");

        mRecyclerView = (RecyclerView) findViewById(R.id.comments_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbarComments);
        mToolbar.setTitle(" " + repo + " comments");
        mToolbar.setLogo(R.drawable.ic_github);

        setTitle(R.string.activity_comment);
        setSupportActionBar(mToolbar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(layoutManager);

        commentsPresenter.getComments(user, repo);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        commentsPresenter.onDetach();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showComments(List<Comment> comments) {
        CommentAdapter adapter = new CommentAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setDataSource(comments);
    }
}
