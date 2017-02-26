package com.yuliya.reposongithub.flow.comments.view;

import android.content.Context;

import com.yuliya.reposongithub.flow.comments.CommentsDataSource;
import com.yuliya.reposongithub.flow.comments.CommentsRepository;
import com.yuliya.reposongithub.model.Comment;

import java.util.List;

import rx.Single;
import rx.Subscription;
import rx.internal.util.SubscriptionList;

/**
 * Created by Yuliya on 22.02.2017.
 */

public class CommentsPresenter implements CommentsDataSource {

    private CommentsRepository commentsRepository = new CommentsRepository();

    private CommentsView commentsView = null;

    private SubscriptionList subscriptionList = new SubscriptionList();

    public void onAttach(CommentsView commentsView){
        commentsRepository.init(commentsView.getContext());
        this.commentsView = commentsView;
    }

    public void onDetach(){
        subscriptionList.unsubscribe();
    }

    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        Single<List<Comment>> single = commentsRepository.getComments(user, repo);
        Subscription subscription = single.subscribe(
                list -> commentsView.showComments(list),
                Throwable::printStackTrace);
        subscriptionList.add(subscription);
        return single;
    }

    @Override
    public void clearComments() {
        commentsRepository.clearComments();
    }

    @Override
    public void init(Context context) {

    }
}
