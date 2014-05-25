package org.opencivicdata.android.opencivicdata.adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */
abstract public class GenericListAdaptor<E> extends BaseAdapter {
    protected ArrayList<E> arrayList;
    protected Context context;

    public GenericListAdaptor(Context context) {
        this.context = context;
        this.arrayList = new ArrayList<E>();
    }

    public void add(E object) {
        this.arrayList.add(object);
        this.notifyDataSetChanged();
    }

    public void add(Iterator<E> objs) {
        while (objs.hasNext()) {
            this.arrayList.add(objs.next());
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
