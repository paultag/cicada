package org.opencivicdata.android.opencivicdata.adaptors;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import org.opencivicdata.android.opencivicdata.dao.PaginatedList;
import org.opencivicdata.android.opencivicdata.tasks.GenericListPopulator;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

public class GenericListScrollManager<E> implements AbsListView.OnScrollListener {
    PaginatedList<E> paginatedList;
    GenericListAdaptor<E> genericListAdaptor;
    ProgressBar progressBar;
    boolean loading;

    public GenericListScrollManager(
            GenericListAdaptor<E> genericListAdaptor,
            ProgressBar pb,
            PaginatedList<E> pl
    ) {
        this.paginatedList = pl;
        this.loading = false;
        this.progressBar = pb;
        this.genericListAdaptor = genericListAdaptor;
    }

    protected void doLoad() {
        this.loading = true;
        if (this.paginatedList.hasNextPage()) {
            GenericListPopulator<E> genericListPopulator = new GenericListPopulator<E>(
                    this,
                    this.genericListAdaptor
            );
            genericListPopulator.execute(this.paginatedList);
        } /* Else: End of List. */
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {}

    @Override
    public void onScroll(
            AbsListView absListView,
            int firstVisible,
            int visibleCount,
            int totalCount
    ) {
        if (this.loading) {
            return;
        }

        if ((firstVisible + visibleCount) >= totalCount) {
            this.doLoad();
        }
    }

    public void setLoading() {
        this.loading = true;
        this.progressBar.setVisibility(View.VISIBLE);
    }

    public void setLoaded() {
        this.loading = false;
        this.progressBar.setVisibility(View.GONE);
    }
}
