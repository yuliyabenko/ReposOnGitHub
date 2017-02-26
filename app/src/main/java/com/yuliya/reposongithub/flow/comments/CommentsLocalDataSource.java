package com.yuliya.reposongithub.flow.comments;

import com.yuliya.reposongithub.base.BaseLocalDataSource;
import com.yuliya.reposongithub.model.Comment;

import java.util.List;

import io.realm.RealmResults;
import rx.Single;

/**
 * Created by Yuliya on 22.02.2017.
 */

public class CommentsLocalDataSource extends BaseLocalDataSource
        implements CommentsDataSource {

    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        return Single.create(subscriber -> {
            realm.executeTransaction(innerRealm -> {
                RealmResults<Comment> results = innerRealm.where(Comment.class).equalTo("user", user)
                        .equalTo("repo", repo).findAll();
                if(results == null) {
                    subscriber.onError(new Exception("vse ploho"));
                } else {
                    List<Comment> repos = innerRealm.copyFromRealm(results);
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
    public void clearComments() {

    }

    public Single<List<Comment>> saveRepo(List<Comment> list) {
        realm.executeTransaction(realm2 -> {
            realm.copyToRealmOrUpdate(list);
        });
        return Single.just(list);
    }
}
