package com.yuliya.reposongithub.flow.repos.view;

import android.content.Context;

import com.yuliya.reposongithub.flow.repos.ReposDataSource;
import com.yuliya.reposongithub.flow.repos.ReposRepository;
import com.yuliya.reposongithub.model.Repo;

import java.util.List;

import rx.Single;
import rx.Subscription;
import rx.internal.util.SubscriptionList;

/**
 * Created by Yuliya on 13.02.2017.
 */

public class ReposPresenter implements ReposDataSource {

    private ReposRepository reposRepository = new ReposRepository();

    private ReposView reposView = null;

    private SubscriptionList subscriptionList = new SubscriptionList();


    public void onAttach(ReposView reposView){
        reposRepository.init(reposView.getContext());
        this.reposView = reposView;
    }

    public void onDetach(){
        subscriptionList.unsubscribe();
    }

    @Override
    public Single<List<Repo>> getRepos(String user) {
        Single<List<Repo>> single = reposRepository.getRepos(user);
        Subscription subscription = single.subscribe(
                list -> reposView.showRepos(list),
                Throwable::printStackTrace);
        subscriptionList.add(subscription);
        return single;
    }

    @Override
    public void clearRepos() {
        reposRepository.clearRepos();
    }

    @Override
    public void init(Context context) {

    }
}
