package com.machichi.app.fcttest.db_greendao_add_list;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import hugo.weaving.DebugLog;
import mybdd.DaoMaster;
import mybdd.DaoSession;

public class DaoExampleApplication extends Application {

    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }
    @DebugLog
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "profil-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    @DebugLog
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
