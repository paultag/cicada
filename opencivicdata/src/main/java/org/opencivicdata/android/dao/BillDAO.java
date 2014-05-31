package org.opencivicdata.android.dao;

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

import org.opencivicdata.android.models.Bill;

/**
 * The Bill Data Access Object contains common and generic
 * methods to handle the retrieval of data from a generic
 * backend.
 *
 * Any implementation of a Bill backend should implement
 * a DAO to interface to the storage backend.
 */
public interface BillDAO {
    /**
     * Get a bill by it's Open Civic Data ID
     *
     * @param openCivicDataId
     * @return
     */
    public Bill getBillByOpenCivicDataID(String openCivicDataId);

    /**
     * Get a list of Bill objects by their Title.
     *
     * @param title
     * @return
     */
    public PaginatedList<Bill> getBillsByTitle(String title);

    /**
     * Get a list of Bill objects by their Name (SB1, HB 201)
     *
     * @param name
     * @return
     */
    public PaginatedList<Bill> getBillsByName(String name);
}
