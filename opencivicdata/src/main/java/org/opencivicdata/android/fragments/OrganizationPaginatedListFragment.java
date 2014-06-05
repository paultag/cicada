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

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.opencivicdata.android.R;
import org.opencivicdata.android.support.GenericListScrollManager;
import org.opencivicdata.android.adaptors.OrganizationAdaptor;
import org.opencivicdata.android.dao.OrganizationDAO;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.dao.api.OrganizationAPIDAO;
import org.opencivicdata.android.models.Organization;

/**
 * OrganizationListFragment is the simple fragment to handle listing Organizations. Yes,
 * I know that's entirely unhelpful, but that's what it does, and that's how it does it.
 */
public class OrganizationPaginatedListFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.list, container, false);
        ListView lv = (ListView) linearLayout.findViewById(android.R.id.list);

        final OrganizationAdaptor organizationAdaptor = new OrganizationAdaptor(this.getActivity());
        lv.setAdapter(organizationAdaptor);
        ProgressBar pb = (ProgressBar) linearLayout.findViewById(R.id.list_loading);

        OrganizationDAO organizationDAO = new OrganizationAPIDAO();
        PaginatedList<Organization> paginatedList = organizationDAO.getOrganizationsByJurisdiction(
                "ocd-jurisdiction/country:us/state:ma/legislature"
        );

        GenericListScrollManager<Organization> genericListScrollManager = new GenericListScrollManager<Organization>(
                organizationAdaptor,
                paginatedList
        );
        genericListScrollManager.setProgressBar(pb);
        lv.setOnScrollListener(genericListScrollManager);

        return linearLayout;
    }
}
