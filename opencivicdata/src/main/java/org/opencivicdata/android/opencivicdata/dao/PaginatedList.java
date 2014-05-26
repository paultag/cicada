package org.opencivicdata.android.opencivicdata.dao;

import org.opencivicdata.android.opencivicdata.dao.api.APIBase;

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

public interface PaginatedList<E> {
    public Iterator<E> getNextPage();
    public boolean hasNextPage();
}
