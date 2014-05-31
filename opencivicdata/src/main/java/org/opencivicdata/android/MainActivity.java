package org.opencivicdata.android;

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

import org.opencivicdata.android.dao.api.APIBase;
import org.opencivicdata.android.fragments.BillListFragment;
import org.opencivicdata.android.fragments.OrganizationListFragment;
import org.opencivicdata.android.fragments.PersonListFragment;
import org.opencivicdata.android.support.TabbedActionBarActivity;

/**
 * MainActivity is the main entrypoint to the application, the default home
 * view. This activity is basially just a tabbed view composed of a few
 * Fragments.
 */
public class MainActivity extends TabbedActionBarActivity {
	@Override
	public void addTabs() {
        APIBase.setApiKey(this.getResources().getString(R.string.api_key));
        APIBase.setApiHost(this.getResources().getString(R.string.ocd_api_host));

        this.addTab(new PersonListFragment(), "People");
		this.addTab(new OrganizationListFragment(), "Organizations");
        this.addTab(new BillListFragment(), "Bills");
		this.addTab(new PersonListFragment(), "People Two");
	}
}
