package org.opencivicdata.android.support;

/*
 * This file is a part of Open Civic Data.
 *
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions
 * of the BSD-3 license in the LICENSE file contained in this source
 * distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Simple Fragment wrapper. Not much here. Move along, folks.
 */
public class FragmentViewPager extends FragmentPagerAdapter {
	protected ArrayList<Fragment> fragments;

	public FragmentViewPager(FragmentManager fm) {
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
