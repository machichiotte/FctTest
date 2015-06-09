package com.machichi.app.fcttest.db_greendao_add_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.machichi.app.fcttest.R;

import hugo.weaving.DebugLog;
import mybdd.Profil;

public class EditProfilActivity extends Fragment {

    private Button btnSave;

    private EditText etProfilNick;
    private EditText etProfilMail;
    private EditText etProfilNum;
    private EditText etProfilPassword;

    private long profilId;
    private Profil profil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bddgreen_activity_edit_box, container, false);

        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        etProfilNick = (EditText) rootView.findViewById(R.id.etProfilNick);
        etProfilMail = (EditText) rootView.findViewById(R.id.etProfilMail);
        etProfilNum = (EditText) rootView.findViewById(R.id.etProfilNum);
        etProfilPassword = (EditText) rootView.findViewById(R.id.etProfilPassword);

        if (getActivity().getIntent() != null && getActivity().getIntent().getExtras() != null) {
            profilId = getActivity().getIntent().getExtras().getLong("profilId");
            profil = ProfilRepository.getProfilForId(getActivity(), profilId);
        }

        setupButtons();
        fillViewWithData();
        return rootView;
    }

    @DebugLog
    private void setupButtons() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    if (profil == null) {
                        profil = new Profil();
                    } else {
                        profil.setId(profilId);
                    }
                    profil.setNickname(etProfilNick.getText().toString());
                    profil.setMail(etProfilMail.getText().toString());
                    profil.setNum(etProfilNum.getText().toString());
                    profil.setPassword(etProfilPassword.getText().toString());
                    ProfilRepository.insertOrUpdate(getActivity(), profil);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.toast_validation_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @DebugLog
    private boolean validateFields() {
        if (etProfilNick.getText().length() == 0) {
            etProfilNick.setError(getString(R.string.error_cannot_be_empty));
            return false;
        }
        if (etProfilPassword.getText().length() == 0) {
            etProfilPassword.setError(getString(R.string.error_cannot_be_empty));
            return false;
        }
        //TODO Si nombre
        /*try {
            Integer.parseInt(etProfilMail.getText().toString());
        } catch (Exception e) {
            etProfilMail.setError(getString(R.string.error_must_be_number));
            return false;
        }*/

        etProfilNick.setError(null);
        etProfilPassword.setError(null);
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (profil != null) {
        inflater.inflate(R.menu.bddgreen_edit_item_menu, menu);
        } else {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (profil != null) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.bddgreen_edit_item_menu, menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_item:
                deleteItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @DebugLog
    private void deleteItem() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.dialog_delete_item_title))
                .setMessage(R.string.dialog_delete_item_content)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_delete_items_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProfilRepository.deleteProfilWithId(getActivity(), profilId);
                        dialog.cancel();
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.dialog_delete_items_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }

    @DebugLog
    private void fillViewWithData() {
        if (profil != null) {
            etProfilNick.setText(profil.getNickname());
            etProfilMail.setText(profil.getMail());
            etProfilNum.setText(profil.getNum());
            etProfilPassword.setText(profil.getPassword());
        }
    }

}
