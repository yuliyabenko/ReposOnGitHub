package com.yuliya.retrofittest;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.yuliya.retrofittest.flow.repos.view.ReposAdapter;
import com.yuliya.retrofittest.flow.repos.view.ReposAdapter.ReposViewHolder;
import com.yuliya.retrofittest.flow.repos.view.ReposPresenter;
import com.yuliya.retrofittest.flow.repos.view.ReposView;
import com.yuliya.retrofittest.model.Repo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class ReposActivity extends AppCompatActivity   implements
        View.OnClickListener, ReposView {

    private RecyclerView mRecyclerView;

    private Toolbar mToolbar;

    private SearchView mSearchView;

    private MenuItem searchMenuItem;

    private ReposPresenter reposPresenter = new ReposPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reposPresenter.onAttach(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.repositories_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setTitle(R.string.app_name);
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
    public void onClick(View view) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showRepos(List<Repo> repos) {

        ReposAdapter adapter = new ReposAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setDataSource(repos);
        adapter.setOnItemClickListener( view -> {
            ReposViewHolder holder = (ReposViewHolder) mRecyclerView.findContainingViewHolder(view);
            if(holder == null) return;
            //startActivity(EditNoteActivity.newInstance(this, holder.getNote().getId()));
        });
       // Toast.makeText(this, String.valueOf(repos), Toast.LENGTH_SHORT).show();

    }


}
