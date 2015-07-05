package com.machichi.app.fcttest.imageLoader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.machichi.app.fcttest.R;
import com.machichi.app.fcttest.random.RandomFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;

/**
 * Created by Elias on 05/07/2015.
 */
public class ImageLoaderFragment extends Fragment {

    ImageButton pic;
    ImageLoader imageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.image_loader_fragment, container, false);

        pic = (ImageButton) rootView.findViewById(R.id.pictureButton);
        imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("http://vignette3.wikia.nocookie.net/fantendo/images/e/eb/Mario_SM3DW.png", pic);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Recording started", Toast.LENGTH_LONG).show();

                // Create new fragment and transaction
                Fragment newFragment = new RandomFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack

                transaction.replace(R.id.fragment1, newFragment);

                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        return rootView;
    }
}
