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

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;


/**
 * GenericTabListener
 */
public class GenericTabListener implements ActionBar.TabListener {

    ViewPager viewPager;

    public GenericTabListener(ViewPager viewPager) {
	    this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        this.viewPager.setCurrentItem(tab.getPosition());
    }
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}

}
