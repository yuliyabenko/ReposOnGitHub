package com.yuliya.reposongithub;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.yuliya.reposongithub.flow.repos.view.ReposAdapter;
import com.yuliya.reposongithub.flow.repos.view.ReposPresenter;
import com.yuliya.reposongithub.flow.repos.view.ReposView;
import com.yuliya.retrofittest.R;
import com.yuliya.reposongithub.model.Repo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

public class ReposActivity extends AppCompatActivity   implements
        View.OnClickListener, ReposView {

    private RecyclerView mRecyclerView;

    private Toolbar mToolbar;

    private SearchView mSearchView;

    private ReposPresenter reposPresenter = new ReposPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reposPresenter.onAttach(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.repositories_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setLogo(R.drawable.ic_github);
        setTitle(" Repositories on GitHub");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reposPresenter.onDetach();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        RxSearchView.queryTextChanges(mSearchView)
                .debounce(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(query -> reposPresenter.getRepos(query.toString()));
        return true;
    }

    @Override
    public void onClick(View view) { }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showRepos(List<Repo> repos) {
        ReposAdapter adapter = new ReposAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setDataSource(repos);
        adapter.setOnItemClickListener( view -> {
            ReposAdapter.ReposViewHolder holder = (ReposAdapter.ReposViewHolder) mRecyclerView.findContainingViewHolder(view);
            if(holder == null) return;
            Intent intent = new Intent(this, CommentsActivity.class);
            intent.putExtra("user", holder.getRepo().getOwner().getLogin());
            intent.putExtra("repo", holder.getRepo().getName());
            startActivity(intent);
        });
    }
}
