package com.machichi.app.fcttest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.machichi.app.fcttest.camera.CameraFragment;
import com.machichi.app.fcttest.db.DbItemFragment;
import com.machichi.app.fcttest.random.RandomFragment;
import com.machichi.app.fcttest.rank.list.RankListFragment;
import com.machichi.app.fcttest.soundbox.SoundBoxFragment;

public class MainActivity extends Activity {

	// Declaring our tabs and the corresponding fragments.
	ActionBar.Tab rankTab, soundBoxTab, cameraTab, randomTab, dbItemTab;
	Fragment rankFragment = new RankListFragment();
	Fragment cameraFragment = new CameraFragment();
	Fragment soundBoxFragment = new SoundBoxFragment();
    Fragment randomFragment = new RandomFragment();
    Fragment dbItemFragment = new DbItemFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Asking for the default ActionBar element that our platform supports.
		ActionBar actionBar = getActionBar();
		 
        // Screen handling while hiding ActionBar icon.
        actionBar.setDisplayShowHomeEnabled(false);
 
        // Screen handling while hiding Actionbar title.
        actionBar.setDisplayShowTitleEnabled(false);
 
        // Creating ActionBar tabs.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Setting custom tab icons.
        rankTab = actionBar.newTab().setIcon(R.drawable.logo_rank);
        cameraTab = actionBar.newTab().setIcon(R.drawable.logo_camera);
        soundBoxTab = actionBar.newTab().setIcon(R.drawable.logo_soundbox);
            randomTab = actionBar.newTab().setIcon(R.drawable.logo_random);
            dbItemTab = actionBar.newTab().setIcon(R.drawable.logo_random);

        // Setting tab listeners.
            rankTab.setTabListener(new TabListener(rankFragment));
        cameraTab.setTabListener(new TabListener(cameraFragment));
        soundBoxTab.setTabListener(new TabListener(soundBoxFragment));
        randomTab.setTabListener(new TabListener(randomFragment));
        dbItemTab.setTabListener(new TabListener(dbItemFragment));

        // Adding tabs to the ActionBar.
        actionBar.addTab(rankTab);
        actionBar.addTab(cameraTab);
        actionBar.addTab(soundBoxTab);
        actionBar.addTab(randomTab);
        actionBar.addTab(dbItemTab);
	}
}
