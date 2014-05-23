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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.R;
import org.opencivicdata.android.opencivicdata.adaptors.PersonAdaptor;

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
        ListView lv = (ListView) inflater.inflate(R.layout.list, container, false);
        lv.setAdapter(new PersonAdaptor(this.getActivity()));
        return lv;
    }
}
