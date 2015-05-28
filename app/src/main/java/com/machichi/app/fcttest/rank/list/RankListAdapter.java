package com.machichi.app.fcttest.rank.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.machichi.app.fcttest.R;

import java.util.ArrayList;

/**
 * Created by Elias on 25/05/2015.
 */
public class RankListAdapter extends BaseAdapter {
    private static ArrayList<RankListItem> listRank;

    private LayoutInflater mInflater;

    public RankListAdapter(Context photosFragment, ArrayList<RankListItem> results){
        listRank = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listRank.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listRank.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_list_rank, null);
            holder = new ViewHolder();
            holder.txt_a = (TextView) convertView.findViewById(R.id.lv_item_rank_a);
            holder.txt_b = (TextView) convertView.findViewById(R.id.lv_item_rank_b);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_a.setText(listRank.get(position).getName());
        holder.txt_b.setText(listRank.get(position).getPhone());

        return convertView;
    }

    static class ViewHolder{
        TextView txt_a, txt_b;
    }
}