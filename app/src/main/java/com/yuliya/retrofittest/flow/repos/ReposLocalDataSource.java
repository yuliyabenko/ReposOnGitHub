package com.yuliya.retrofittest.flow.repos;

import com.yuliya.retrofittest.base.BaseLocalDataSource;
import com.yuliya.retrofittest.model.Repo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Single;

/**
 * Created by Yuliya on 08.02.2017.
 */

public class ReposLocalDataSource extends BaseLocalDataSource
                                implements ReposDataSource{
    @Override
    public Single<List<Repo>> getRepos(String user) {
        return Single.create(subscriber -> {
            realm.executeTransaction(innerRealm -> {
                RealmResults<Repo> results = innerRealm.where(Repo.class).findAll();
                if(results == null) {
                    subscriber.onError(new Exception("vse ploho"));
                } else {
                    List<Repo> repos = innerRealm.copyFromRealm(results);
                    if(repos != null) {
                        subscriber.onSuccess(repos);
                    } else {
                        subscriber.onError(new Exception("vse ploho2"));
                    }
                }
            });
        });
    }

    @Override
    public void clearRepos() {
        
    }

    public Single<List<Repo>> saveRepo(List<Repo> list) {
        realm.executeTransaction(realm1 -> {
            realm.copyToRealmOrUpdate(list);
        });
        return Single.just(list);
    }
}
