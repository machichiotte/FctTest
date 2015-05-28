package com.machichi.app.fcttest.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.machichi.app.fcttest.R;

import java.util.List;

/**
 * Created by Elias on 26/05/2015.
 */
public class DbItemAdapter extends BaseAdapter {

    public static final String TAG = DbItemFragment.class.getName();

    private LayoutInflater inflater;

    private List<DbItem> items = null;

    public DbItemAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<DbItem> details) {
        this.items = details;
    }

    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        if (items == null || items.get(position) == null) {
            return null;
        }
        return items.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.db_item_listitem, parent, false);
        }

        DbItem item = items.get(position);

        if (item != null) {
            ((TextView) currentView.findViewById(R.id.db_tv_A)).setText(item.getDb_string_A());
            ((TextView) currentView.findViewById(R.id.db_tv_B)).setText(Long.toString(item.getDb_long_A()));
        }

        return currentView;
    }
}
