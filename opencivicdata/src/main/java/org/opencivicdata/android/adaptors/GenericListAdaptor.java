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
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Generic type ListAdaptor is a standard Adapter (which extends
 * BaseAdapter), with hard pre-set values for Open Civic Data lists. In
 * particular, it handles the creation of the ArrayList to hold the type
 * parameter <E>.
 *
 * This also defines the interface to add (or bulk-add) data to the List
 * from a different process, such as a Background task.
 *
 * @param <E> Type to store in the backend List.
 */
abstract public class GenericListAdaptor<E> extends BaseAdapter {
    protected ArrayList<E> arrayList;
    protected Context context;

    public GenericListAdaptor(Context context) {
        this.context = context;
        this.arrayList = new ArrayList<E>();
    }

    /**
     * Add object of parametrized type <E> to the internal
     * ArrayList.
     *
     * @param object object to add
     */
    public void add(E object) {
        this.arrayList.add(object);
        this.notifyDataSetChanged();
    }

    /**
     * Add objects of type <E> to the internal ArrayList.
     *
     * @param objs Iterator of objects of type <E> to add
     */
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
