package org.opencivicdata.android.opencivicdata.fragments;
/*
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.R;
import org.opencivicdata.android.opencivicdata.adaptors.OrganizationAdaptor;
import org.opencivicdata.android.opencivicdata.dao.OrganizationDAO;
import org.opencivicdata.android.opencivicdata.dao.api.OrganizationAPIDAO;
import org.opencivicdata.android.opencivicdata.models.Organization;
import org.opencivicdata.android.opencivicdata.models.Person;
import org.opencivicdata.android.opencivicdata.tasks.GenericListPopulator;

import java.util.Iterator;
import java.util.concurrent.Callable;


/**
 * OrganizationListFragment is the simple fragment to handle listing Organizations. Yes,
 * I know that's entirely unhelpful, but that's what it does, and that's how it does it.
 *
 * Bundle arguments I know about:
 *   - (none)
 */
public class OrganizationListFragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        Bundle bundle = this.getArguments();

        ListView lv = (ListView) inflater.inflate(R.layout.list, container, false);
        // lv.setEmptyView(inflater.inflate(R.layout.empty, container, false));
        OrganizationAdaptor organizationAdaptor = new OrganizationAdaptor(this.getActivity());
        lv.setAdapter(organizationAdaptor);

        GenericListPopulator<Organization> glp = new GenericListPopulator<Organization>(
                organizationAdaptor);

        final OrganizationDAO organizationDAO = new OrganizationAPIDAO();
        glp.execute(new Callable<Iterator<Organization>>() {
            @Override
            public Iterator<Organization> call() throws Exception {
                Log.i("PAUL", "Getting orgs");
                return organizationDAO.getOrganizationsByJurisdiction(
                        "ocd-jurisdiction/country:us/state:ma/legislature");
            }
        });

        return lv;
    }
}
