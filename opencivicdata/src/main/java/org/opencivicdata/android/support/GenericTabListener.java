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
 * GenericTabListener watches for events on a Tabbed activity
 * and changes the state of the ViewPager to match. This class is
 * composed by the TabbedActionBarActivity to handle it's events
 * automagically.
 */
public class GenericTabListener implements ActionBar.TabListener {

    ViewPager viewPager;

    public GenericTabListener(ViewPager viewPager) {
	    this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        /* When we see that we've got a tab click, we'll
         * change up the ViewPager to match. */
        this.viewPager.setCurrentItem(tab.getPosition());
    }
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}

}
