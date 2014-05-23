package org.opencivicdata.android.opencivicdata;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.opencivicdata.android.opencivicdata.fragments.OrganizationListFragment;
import org.opencivicdata.android.opencivicdata.fragments.PersonListFragment;
import org.opencivicdata.android.opencivicdata.listener.GenericTabListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public class MainActivityViewPager extends FragmentPagerAdapter {
        protected ArrayList<Fragment> fragments;

        public MainActivityViewPager(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<Fragment>();
            this.fragments.add(new PersonListFragment());
            this.fragments.add(new OrganizationListFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_pager);
        viewPager.setAdapter(new MainActivityViewPager(this.getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
                }
        );

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab().setText("People");
        ActionBar.Tab tab2 = actionBar.newTab().setText("Organization");

        GenericTabListener genericTabListener = new GenericTabListener(viewPager);

        tab1.setTabListener(genericTabListener);
        tab2.setTabListener(genericTabListener);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
    }

}
