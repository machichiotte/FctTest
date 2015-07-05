package com.machichi.app.fcttest.db_greendao_add_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.machichi.app.fcttest.R;

import hugo.weaving.DebugLog;
import mybdd.Profil;


public class ProfilListActivity extends Fragment {
//public class ProfilListActivity extends Activity {

    private ListView lvItemList;
    private DbItemsAdapter boxAdapter;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bddgreen_activity_box_list);

        lvItemList = (ListView) this.findViewById(R.id.lvItemList);
        boxAdapter = new DbItemsAdapter(ProfilListActivity.this);
        lvItemList.setAdapter(boxAdapter);

        setupButtons();
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bddgreen_activity_box_list, container, false);


        lvItemList = (ListView) rootView.findViewById(R.id.lvItemList);
        boxAdapter = new DbItemsAdapter(getActivity());
        lvItemList.setAdapter(boxAdapter);

        setupButtons();


        return rootView;
    }

    @DebugLog
    private void setupButtons() {
        lvItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editItemIntent = new Intent(getActivity(), EditProfilActivity.class);

//                Box clickedBox = boxAdapter.getItem(position);
                Profil clickedProfil = boxAdapter.getItem(position);
                editItemIntent.putExtra("boxId", clickedProfil.getId());

                startActivity(editItemIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        boxAdapter.updateData(ProfilRepository.getAllProf(getActivity().getApplicationContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bddgreen_profil_list_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                createItem();
                return true;

            case R.id.delete_items:
                clearAllItems();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @DebugLog
    private void createItem() {
        Intent addProfilActivityIntent = new Intent(getActivity(), EditProfilActivity.class);
        startActivity(addProfilActivityIntent);
    }

    @DebugLog
    private void clearAllItems() {
        if (boxAdapter.getCount() == 0) {
            Toast.makeText(getActivity(), getString(R.string.toast_no_items_to_delete), Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.dialog_delete_items_title))
                    .setMessage(R.string.dialog_delete_items_content)
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_delete_items_confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ProfilRepository.clearProf(getActivity());
                            boxAdapter.updateData(ProfilRepository.getAllProf(getActivity()));
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton(R.string.dialog_delete_items_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }
}
