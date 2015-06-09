package com.machichi.app.fcttest.db_greendao_add_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.machichi.app.fcttest.R;

import java.util.List;

import hugo.weaving.DebugLog;
import mybdd.Profil;

public class DbItemsAdapter extends ArrayAdapter<Profil> {

    private LayoutInflater inflater;
    private Context context;
    @DebugLog
    public DbItemsAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @DebugLog
    public void updateData(List<Profil> boxList) {
        this.clear();
        for (Profil aBoxList : boxList) {
            add(aBoxList);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bddgreen_item_box, null);
            viewHolder = new ViewHolder();
            viewHolder.root = (LinearLayout) convertView.findViewById(R.id.boxItem);
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvItemId);
            viewHolder.tvNickname = (TextView) convertView.findViewById(R.id.tvNickname);
            viewHolder.tvMail = (TextView) convertView.findViewById(R.id.tvMail);
            viewHolder.tvNum = (TextView) convertView.findViewById(R.id.tvNum);
            viewHolder.tvPassword = (TextView) convertView.findViewById(R.id.tvPassword);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        editBackground(position, viewHolder);
        fillViewWithData(position, viewHolder);

        return convertView;
    }
    @DebugLog
    private void editBackground(int position, ViewHolder viewHolder) {
        if (position % 2 == 0) {
            viewHolder.root.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            viewHolder.root.setBackgroundColor(context.getResources().getColor(R.color.lightGray));
        }
    }
    @DebugLog
    private void fillViewWithData(int position, ViewHolder viewHolder) {
        viewHolder.tvId.setText(context.getString(R.string.tv_label_item_id) + " " + getItem(position).getId().toString());
        viewHolder.tvNickname.setText(context.getString(R.string.tv_label_profil_nickname) + " " + getItem(position).getNickname());
        viewHolder.tvMail.setText(context.getString(R.string.tv_label_profil_num) + " " + getItem(position).getNum());
        viewHolder.tvNum.setText(context.getString(R.string.tv_label_profil_mail) + " " + getItem(position).getMail());
        //TODO ajout password
        viewHolder.tvNum.setText(context.getString(R.string.tv_label_profil_mail) + " " + getItem(position).getPassword());
    }

    static class ViewHolder {
        LinearLayout root;
        TextView tvId;
        TextView tvNickname;
        TextView tvMail;
        TextView tvNum;
        TextView tvPassword;
    }
}
