package com.machichi.app.fcttest.soundbox;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.machichi.app.fcttest.R;

public class SoundBoxFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_soundbox, container, false);


        Button one = (Button) rootView.findViewById(R.id.button_soundbox_A);
        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.sonar);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mp.start();
            }
        });


        return rootView;
    }
}
