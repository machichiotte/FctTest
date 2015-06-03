package com.machichi.app.fcttest;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.machichi.app.fcttest.camera.CameraFragment;
import com.machichi.app.fcttest.cheese.CheeseListFragment;
import com.machichi.app.fcttest.db.DbItemFragment;
import com.machichi.app.fcttest.random.RandomFragment;
import com.machichi.app.fcttest.soundbox.SoundBoxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "FloatingActionButton", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CheeseListFragment(), "Category 1");
        adapter.addFragment(new SoundBoxFragment(), "Category 2");
        adapter.addFragment(new CameraFragment(), "Category 3");
        adapter.addFragment(new RandomFragment(), "Category 4");
        adapter.addFragment(new DbItemFragment(), "Category 5");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}





/*
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
*/
