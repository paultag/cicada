package org.opencivicdata.android.opencivicdata.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.adaptors.GenericListAdaptor;
import org.opencivicdata.android.opencivicdata.dao.api.iterators.GenericAPIIterator;

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
public class GenericListPopulator<E> extends AsyncTask<Callable<Iterator<E>>, E, Void> {

    protected GenericListAdaptor<E> adaptor;

    public GenericListPopulator(GenericListAdaptor<E> adaptor) {
        this.adaptor = adaptor;
    }



    @Override
    protected Void doInBackground(Callable<Iterator<E>>... callables) {
        Log.i("PAUL", "Doing in Background");
        for (Callable<Iterator<E>> callable : callables) {
            Iterator<E> results = null;
            try {
                results = callable.call();
            } catch (Exception e) {
                Log.e("PAUL", e.toString());
            }
            if (results != null) {
                while (results.hasNext()) {
                    E obj = results.next();
                    this.publishProgress(obj);
                }
                Log.i("PAUL", " -> told updated");
            } else {
                Log.w("PAUL", "Crap, got a null response");
            }
        }
        return (null);
    }

    @Override
    protected void onProgressUpdate(E... items) {
        for (E e : items) {
            this.adaptor.add(e);
        }
    }
}
