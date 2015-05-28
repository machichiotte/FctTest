package com.machichi.app.fcttest.rank.list;

import hugo.weaving.DebugLog;

/**
 * Created by Elias on 25/05/2015.
 */
public class RankListItem {

    String name, phone;

    @DebugLog
    public RankListItem (){

    }

    /*public ListviewContactItem(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
