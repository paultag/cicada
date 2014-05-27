package org.opencivicdata.android.opencivicdata.dao;

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

import java.util.Iterator;

/**
 * PaginatedList interface for DAO objects to fill.
 *
 * This is very useful for DAO routines that fetch data
 * over a remote API that is paginated, which is very common
 * for large datasets. This enforces that we properly handle
 * such datasets by using this interface rather than something
 * Iterator based.
 *
 * @param <E> Type of object we Paginate.
 */
public interface PaginatedList<E> {

    /**
     * Get the next page in the sequence, and return back
     * an Iterator of <E> type objects.
     *
     * @return an Iterator of <E> typed objects.
     */
    public Iterator<E> getNextPage();

    /**
     * Check to see if we've got another page in the data
     * sequence.
     *
     * @return boolean evaluation
     */
    public boolean hasNextPage();
}
