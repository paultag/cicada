package org.opencivicdata.android.tasks;

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

import android.os.AsyncTask;
import android.util.Log;

import org.opencivicdata.android.adaptors.GenericListAdaptor;
import org.opencivicdata.android.support.GenericListScrollManager;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.exceptions.OpenCivicDataRetrievalException;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericListPopulator<E> extends AsyncTask<PaginatedList<E>, Void, Iterator<E>> {
    private static final String TAG = GenericListPopulator.class.getName();

    protected GenericListAdaptor<E> adaptor;
    GenericListScrollManager<E> gsm;

    public GenericListPopulator(GenericListScrollManager<E> gsm, GenericListAdaptor<E> adaptor) {
        this.adaptor = adaptor;
        this.gsm = gsm;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.gsm.setLoading();
    }

    @Override
    protected Iterator<E> doInBackground(PaginatedList<E>... results) {
        Log.i(this.TAG, "Doing in Background");
        ArrayList<E> returnData = new ArrayList<E>();

        for (PaginatedList<E> result : results) {

            if (result.hasNextPage()) {
                Iterator<E> x = result.getNextPage();
                while (x.hasNext()) {
                    returnData.add(x.next());
                }
            } else {
                throw new OpenCivicDataRetrievalException("No more pages. Sorry bout that.");
            }
        }

        return returnData.iterator();
    }

    @Override
    protected void onPostExecute(Iterator<E> objs) {
        this.adaptor.add(objs);
        this.gsm.setLoaded();
    }
}
