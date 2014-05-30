package org.opencivicdata.android.fragments;

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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.opencivicdata.android.R;
import org.opencivicdata.android.support.GenericListScrollManager;
import org.opencivicdata.android.adaptors.PersonAdaptor;
import org.opencivicdata.android.dao.PersonDAO;
import org.opencivicdata.android.dao.api.PersonAPIDAO;
import org.opencivicdata.android.models.Person;

/**
 * OrganizationListFragment is the simple fragment to handle listing Organizations. Yes,
 * I know that's entirely unhelpful, but that's what it does, and that's how it does it.
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

        PersonDAO personDAO = new PersonAPIDAO();
        GenericListScrollManager<Person> genericListScrollManager = new GenericListScrollManager<Person>(
                personAdaptor,
                personDAO.getPeopleByName("John")
        );
        genericListScrollManager.setProgressBar(pb);
        lv.setOnScrollListener(genericListScrollManager);

        return linearLayout;
    }
}
