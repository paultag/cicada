package org.opencivicdata.android.adaptors;

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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.opencivicdata.android.R;
import org.opencivicdata.android.models.Person;

/**
 * PersonAdaptor shims in Person objects into a GenericListAdaptor.
 */
public class PersonAdaptor extends GenericListAdaptor<Person> {

    public PersonAdaptor(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout;

        if (view == null) {
            layout = (LinearLayout) LayoutInflater.from(this.context).inflate(
		            R.layout.person_item, null);
        } else {
            layout = (LinearLayout) view;
        }

        /* Set imageView and friends */
        Person person = (Person) this.getItem(i);
        TextView name = (TextView) layout.findViewById(R.id.person_item_name);
        ImageView imageView = (ImageView) layout.findViewById(R.id.person_item_image);
        name.setText(person.getName());
        Picasso.with(context)
                .load(person.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);

        layout.setTag(person.getId());

        return layout;
    }
}
