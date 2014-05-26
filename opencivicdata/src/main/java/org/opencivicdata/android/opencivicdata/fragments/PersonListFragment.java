package org.opencivicdata.android.opencivicdata.fragments;
/*
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.opencivicdata.android.opencivicdata.R;
import org.opencivicdata.android.opencivicdata.adaptors.PersonAdaptor;
import org.opencivicdata.android.opencivicdata.dao.OrganizationDAO;
import org.opencivicdata.android.opencivicdata.dao.api.OrganizationAPIDAO;
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
public class PersonListFragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.list, container, false);
        ListView lv = (ListView) linearLayout.findViewById(android.R.id.list);

        PersonAdaptor personAdaptor = new PersonAdaptor(this.getActivity());
        lv.setAdapter(personAdaptor);

        ProgressBar pb = (ProgressBar) linearLayout.findViewById(R.id.list_loading);
        GenericListPopulator<Person> glp = new GenericListPopulator<Person>(
                personAdaptor, pb);
        final OrganizationDAO organizationDAO = new OrganizationAPIDAO();

        glp.execute(new Callable<Iterator<Person>>() {
            @Override
            public Iterator<Person> call() throws Exception {
                Log.i("PAUL", "Getting Members");
                return organizationDAO.getMembers(
                        "ocd-organization/926d95da-d78c-11e3-97b1-22000ab81ec3");
            }
        });

        return linearLayout;
    }
}
