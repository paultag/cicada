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

import org.opencivicdata.android.opencivicdata.models.Organization;


/**
 * The Organization Data Access Object contains common and generic
 * methods to handle the retrieval of data from a generic
 * backend.
 *
 * Any implementation of a Organization backend should implement
 * a DAO to interface to the storage backend.
 */
public interface OrganizationDAO {

    /**
     * Get an Organization by it's Open Civic Data ID
     *
     * @param openCivicDataId OCD ID of the requested Organization object.
     * @return an Organization instance if the OCD ID was found, otherwise
     *         it'll return a null.
     */
    public Organization getOrganization(String openCivicDataId);

    /**
     * Get all Organizations in a given Jurisdiction.
     *
     * @param jurisdictionId The Jurisdiction to search for
     *                       Organizations in.
     * @return a PaginatedList of Organization objects.
     */
    public PaginatedList<Organization> getOrganizationsByJurisdiction(String jurisdictionId);
}
