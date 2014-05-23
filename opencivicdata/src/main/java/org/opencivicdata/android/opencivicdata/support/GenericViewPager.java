package org.opencivicdata.android.opencivicdata.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class GenericViewPager extends FragmentPagerAdapter {
	protected ArrayList<Fragment> fragments;

	public GenericViewPager(FragmentManager fm) {
		super(fm);
		this.fragments = new ArrayList<Fragment>();
	}

	public void addFragment(Fragment fragment) {
		this.fragments.add(fragment);
		this.notifyDataSetChanged();
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
