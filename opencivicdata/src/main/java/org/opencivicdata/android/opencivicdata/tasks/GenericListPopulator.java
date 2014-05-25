package org.opencivicdata.android.opencivicdata.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.adaptors.GenericListAdaptor;
import org.opencivicdata.android.opencivicdata.dao.api.iterators.GenericAPIIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */
public class GenericListPopulator<E> extends AsyncTask<Callable<Iterator<E>>, Void, Iterator<E>> {
    private static final String TAG = GenericListPopulator.class.getName();

    protected GenericListAdaptor<E> adaptor;

    public GenericListPopulator(GenericListAdaptor<E> adaptor) {
        this.adaptor = adaptor;
    }

    @Override
    protected Iterator<E> doInBackground(Callable<Iterator<E>>... callables) {
        Log.i(this.TAG, "Doing in Background");
        ArrayList<E> returnData = new ArrayList<E>();

        for (Callable<Iterator<E>> callable : callables) {
            Iterator<E> results = null;
            try {
                results = callable.call();
            } catch (Exception e) {
                Log.e(this.TAG, e.toString());
            }

            if (results != null) {
                while (results.hasNext()) {
                    returnData.add(results.next());
                }
            }
        }

        return returnData.iterator();
    }

    @Override
    protected void onPostExecute(Iterator<E> objs) {
        this.adaptor.add(objs);
    }
}
