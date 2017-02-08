package com.yuliya.retrofittest;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Yuliya on 08.02.2017.
 */

public class Repository extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
