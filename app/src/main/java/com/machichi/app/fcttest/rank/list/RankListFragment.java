package com.machichi.app.fcttest.rank.list;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.machichi.app.fcttest.R;

import java.util.ArrayList;

import hugo.weaving.DebugLog;

public class RankListFragment extends Fragment{

    public RankListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_rank, container, false);

        ArrayList<RankListItem> listRank = GetlistContact();
        ListView lv = (ListView)rootView.findViewById(R.id.lv_rank);
        lv.setAdapter(new RankListAdapter(getActivity(), listRank));

        return rootView;
    }

    private ArrayList<RankListItem> GetlistContact(){
        ArrayList<RankListItem> contactlist = new ArrayList<RankListItem>();

        RankListItem contact = new RankListItem();

        contact.setName("Topher");
        contact.setPhone("01213113568");
        contactlist.add(contact);

        contact = new RankListItem();
        contact.setName("Jean");
        contact.setPhone("01213869102");
        contactlist.add(contact);

        contact = new RankListItem();
        contact.setName("Andrew");
        contact.setPhone("01213123985");
        contactlist.add(contact);

        return contactlist;
    }
}
