package org.opencivicdata.android.opencivicdata.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.R;
import org.opencivicdata.android.opencivicdata.adaptors.PersonAdaptor;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
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
