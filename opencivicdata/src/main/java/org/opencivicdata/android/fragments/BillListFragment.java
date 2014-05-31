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
import org.opencivicdata.android.adaptors.BillAdaptor;
import org.opencivicdata.android.adaptors.OrganizationAdaptor;
import org.opencivicdata.android.dao.BillDAO;
import org.opencivicdata.android.dao.OrganizationDAO;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.dao.api.BillAPIDAO;
import org.opencivicdata.android.dao.api.OrganizationAPIDAO;
import org.opencivicdata.android.models.Bill;
import org.opencivicdata.android.models.Organization;
import org.opencivicdata.android.support.GenericListScrollManager;

/**
 * BillListFragment is the simple fragment to handle listing Bills. Yes,
 * I know that's entirely unhelpful, but that's what it does, and that's how it does it.
 */
public class BillListFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.list, container, false);
        ListView lv = (ListView) linearLayout.findViewById(android.R.id.list);

        final BillAdaptor billAdaptor = new BillAdaptor(this.getActivity());
        lv.setAdapter(billAdaptor);
        ProgressBar pb = (ProgressBar) linearLayout.findViewById(R.id.list_loading);

        BillDAO billDAO = new BillAPIDAO();
        PaginatedList<Bill> paginatedList = billDAO.getBillsByTitle("beer");

        GenericListScrollManager<Bill> genericListScrollManager = new GenericListScrollManager<Bill>(
                billAdaptor,
                paginatedList
        );
        genericListScrollManager.setProgressBar(pb);
        lv.setOnScrollListener(genericListScrollManager);

        return linearLayout;
    }
}
