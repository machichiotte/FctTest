package com.machichi.app.fcttest.random;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.machichi.app.fcttest.R;

import java.util.Random;

/**
 * Created by Elias on 26/05/2015.
 */
public class RandomFragment extends Fragment {

    final Random rnd = new Random();

    public RandomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_random, container, false);

        ImageView iv = (ImageView) rootView.findViewById(R.id.iv_random);

        // TODO: check random value (rnd.nextInt(n) should be n-1)
        final String str = "img_" + rnd.nextInt(3);
        iv.setImageDrawable(getResources().getDrawable(getResourceID(str, "drawable", getActivity())));

        return rootView;
    }

    protected final static int getResourceID(final String resName, final String resType, final Context ctx) {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + resName);
        } else {
            return ResourceID;
        }
    }
}