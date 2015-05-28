package com.machichi.app.fcttest.db;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.machichi.app.fcttest.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hugo.weaving.DebugLog;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Elias on 26/05/2015.
 */
public class DbItemFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = DbItemFragment.class.getName();

    private GridView mGridView;
    private DbItemAdapter mAdapter;

    private Realm realm;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*final View rootView = inflater.inflate(R.layout.fragment_db_item,
                container, false);*/
        rootView = inflater.inflate(R.layout.fragment_db_item,
                container, false);

        // Clear the realm from last time
        Realm.deleteRealmFile(getActivity());

        // Create a new empty instance of Realm
        realm = Realm.getInstance(getActivity());

        return rootView;

    }


    @Override
    public void onResume() {
        super.onResume();

        // Load from file "items.json" first time
        if(mAdapter == null) {
            List<DbItem> items = loadItems();

            //This is the GridView adapter
            mAdapter = new DbItemAdapter(getActivity());
            mAdapter.setData(items);

            //This is the GridView which will display the list of items.json
            mGridView = (GridView)rootView.findViewById(R.id.items_list);
            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(DbItemFragment.this);
            mAdapter.notifyDataSetChanged();
            mGridView.invalidate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

    @DebugLog
    private List<DbItem> loadItems() {
        // In this case we're loading from local assets.
        // NOTE: could alternatively easily load from network
        InputStream stream = null;
        try {
            stream = getActivity().getAssets().open("items.json");
        } catch (IOException e) {
            return null;
        }

        // GSON can parse the data.
        // Note there is a bug in GSON 2.3.1 that can cause it to StackOverflow when working with RealmObjects.
        // To work around this, use the ExclusionStrategy below or downgrade to 1.7.1
        // See more here: https://code.google.com/p/google-gson/issues/detail?id=440
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        JsonElement json = new JsonParser().parse(new InputStreamReader(stream));
        List<DbItem> items = gson.fromJson(json, new TypeToken<List<DbItem>>() {}.getType());

        // Open a transaction to store items into the realm
        // Use copyToRealm() to convert the objects into proper RealmObjects managed by Realm.
        realm.beginTransaction();
        Collection<DbItem> realmItems = realm.copyToRealm(items);
        realm.commitTransaction();

        return new ArrayList<DbItem>(realmItems);
    }

    @DebugLog
    public void updateItems() {
        Realm realm = Realm.getInstance(getActivity());

        // Pull all the items.json from the realm
        RealmResults<DbItem> items = realm.where(DbItem.class).findAll();

        // Put these items in the Adapter
        mAdapter.setData(items);
        mAdapter.notifyDataSetChanged();
        mGridView.invalidate();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DbItem modifiedItem = (DbItem)mAdapter.getItem(position);

        // Update the realm object affected by the user
        Realm realm = Realm.getInstance(getActivity());

        // Acquire the list of realm items.json matching the name of the clicked DbItem.
        DbItem item = realm.where(DbItem.class).equalTo("db_string_A", modifiedItem.getDb_string_A()).findFirst();

        // Create a transaction to increment the vote count for the selected DbItem in the realm
        realm.beginTransaction();
        item.setDb_long_A(item.getDb_long_A() + 1);
        realm.commitTransaction();

        updateItems();
    }
}
