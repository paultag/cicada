package org.opencivicdata.android.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.opencivicdata.android.R;
import org.opencivicdata.android.models.Bill;
import org.opencivicdata.android.models.Organization;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */
public class BillAdaptor extends GenericListAdaptor<Bill> {
    public BillAdaptor(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout;

        if (view == null) {
            layout = (LinearLayout) LayoutInflater.from(this.context).inflate(
                    R.layout.bill_item, null);
        } else {
            layout = (LinearLayout) view;
        }

        /* Set imageView and friends */
        Bill bill = (Bill) this.getItem(i);

        TextView name = (TextView) layout.findViewById(R.id.bill_item_title);
        name.setText(bill.getTitle());

        layout.setTag(bill.getId());

        return layout;
    }
}
