package org.opencivicdata.android.opencivicdata;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.opencivicdata.android.opencivicdata.fragments.OrganizationListFragment;
import org.opencivicdata.android.opencivicdata.fragments.PersonListFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private class MainActivityFragmentAdaptor extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments;

        public MainActivityFragmentAdaptor(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<Fragment>();
            this.fragments.add(new PersonListFragment());
            this.fragments.add(new OrganizationListFragment());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }

        @Override
        public Fragment getItem(int position) {
           return this.fragments.get(position);
        }

        @Override
        public int getCount() {return this.fragments.size();}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        MainActivityFragmentAdaptor gpa = new MainActivityFragmentAdaptor(
            this.getSupportFragmentManager()
        );
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_pager);
        viewPager.setAdapter(gpa);
    }

}
