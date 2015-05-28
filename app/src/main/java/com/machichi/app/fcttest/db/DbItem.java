package com.machichi.app.fcttest.db;

import io.realm.RealmObject;

/**
 * Created by Elias on 26/05/2015.
 */
public class DbItem extends RealmObject {

    private String db_string_A;
    private long db_long_A;

    public String getDb_string_A() {
        return db_string_A;
    }

    public void setDb_string_A(String db_string_A) {
        this.db_string_A = db_string_A;
    }

    public long getDb_long_A() {
        return db_long_A;
    }

    public void setDb_long_A(long db_long_A) {
        this.db_long_A = db_long_A;
    }
}
