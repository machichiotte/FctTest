package com.machichi.app.fcttest.db_greendao_add_list;

import android.content.Context;

import java.util.List;

import hugo.weaving.DebugLog;
import mybdd.Profil;
import mybdd.ProfilDao;

public class ProfilRepository {
    @DebugLog
    public static void insertOrUpdate(Context context, Profil profil) {
        getProfilDao(context).insertOrReplace(profil);
    }
    @DebugLog
    public static void clearProf(Context context) {
        getProfilDao(context).deleteAll();
    }
    @DebugLog
    public static void deleteProfilWithId(Context context, long id) {
        getProfilDao(context).delete(getProfilForId(context, id));
    }
    @DebugLog
    public static Profil getProfilForId(Context context, long id) {
        return getProfilDao(context).load(id);
    }
    @DebugLog
    public static List<Profil> getAllProf(Context context) {
        return getProfilDao(context).loadAll();
    }
    @DebugLog
    private static ProfilDao getProfilDao(Context c) {
        return ((DaoExampleApplication) c.getApplicationContext()).getDaoSession().getProfilDao();
    }
}
