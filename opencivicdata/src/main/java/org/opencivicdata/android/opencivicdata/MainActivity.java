package org.opencivicdata.android.opencivicdata;

/*
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

import org.opencivicdata.android.opencivicdata.dao.api.APIBase;
import org.opencivicdata.android.opencivicdata.fragments.OrganizationListFragment;
import org.opencivicdata.android.opencivicdata.fragments.PersonListFragment;
import org.opencivicdata.android.opencivicdata.support.TabbedActionBarActivity;

/**
 * MainActivity is the main entrypoint to the application, the default home
 * view. This activity is basially just a tabbed view composed of a few
 * Fragments.
 */
public class MainActivity extends TabbedActionBarActivity {
	@Override
	public void addTabs() {
        APIBase.setApiKey("");
        APIBase.setApiHost("http://api.opencivicdata.org");

        this.addTab(new PersonListFragment(), "People");
		this.addTab(new OrganizationListFragment(), "Organizations");
		this.addTab(new PersonListFragment(), "People Two");
	}
}
