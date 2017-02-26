package com.yuliya.reposongithub.base;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by Yuliya on 08.02.2017.
 */

public abstract class BaseLocalDataSource implements BaseDateSource {

    protected Realm realm;

    @Override
    public void init(Context context) {
        realm = Realm.getDefaultInstance();
    }
}
