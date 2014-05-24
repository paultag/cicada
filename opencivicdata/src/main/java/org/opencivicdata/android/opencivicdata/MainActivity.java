package org.opencivicdata.android.opencivicdata;

/*
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

import android.os.Bundle;

import org.opencivicdata.android.opencivicdata.fragments.OrganizationListFragment;
import org.opencivicdata.android.opencivicdata.fragments.PersonListFragment;
import org.opencivicdata.android.opencivicdata.support.GenericActionBarActivity;

/**
 * MainActivity is the main entrypoint to the application, the default home
 * view. This activity is basially just a tabbed view composed of a few
 * Fragments.
 */
public class MainActivity extends GenericActionBarActivity {
	@Override
	public void addTabs() {
        Bundle bundle = new Bundle();
        bundle.putString("jurisdiction", "ocd-jurisdiction/country:us/state:oh/legislature");

        this.addTab(bundle, new PersonListFragment(), "People");
		this.addTab(bundle, new OrganizationListFragment(), "Organizations");
		this.addTab(bundle, new PersonListFragment(), "People Two");
	}
}
