package org.opencivicdata.android.support;

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

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import org.opencivicdata.android.adaptors.GenericListAdaptor;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.tasks.GenericListPopulator;

/**
 * The GenericListScrollManager ensures that a list will continue to consume
 * objects of Type <E> from a PaginatedList as the user scrolls to the
 * bottom of the view.
 *
 * @param <E> Type of object contained in the list.
 */
public class GenericListScrollManager<E> implements AbsListView.OnScrollListener, AbsListView.OnClickListener {
    PaginatedList<E> paginatedList;
    GenericListAdaptor<E> genericListAdaptor;
    ProgressBar progressBar;
    boolean loading;

    public GenericListScrollManager(
            GenericListAdaptor<E> genericListAdaptor,
            PaginatedList<E> pl
    ) {
        this.paginatedList = pl;
        this.loading = false;
        this.genericListAdaptor = genericListAdaptor;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
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

    /**
     * Handle scroll events.
     *
     * We use a hard-coded value for elements from the bottom before loading
     * in the onScroll method. Commonly, we want to load more data before
     * the user gets there, which is why we have a multiplier against the
     * visibleCount.

     * @param absListView ListView to observe.
     * @param firstVisible Integer of the First visible row.
     * @param visibleCount Integer of the number of rows visible.
     * @param totalCount Total number of rows.
     */
    @Override
    public void onScroll(
            AbsListView absListView,
            int firstVisible,
            int visibleCount,
            int totalCount
    ) {
        if (this.loading) {
            /* Short circut if we don't need to do work */
            return;
        }

        /* Load fresh data when we're 1.5 screens from the bottom. */
        if (((firstVisible + visibleCount) + (1.5 * visibleCount)) >= totalCount) {
            this.doLoad();
        }
    }

    public void setLoading() {
        this.loading = true;
        if (this.progressBar != null) {
            this.progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void setLoaded() {
        this.loading = false;
        if (this.progressBar != null) {
            this.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        view.getId();
    }
}
