package org.opencivicdata.android.opencivicdata.dao;

import org.json.JSONException;
import org.opencivicdata.android.opencivicdata.models.Organization;
import org.opencivicdata.android.opencivicdata.models.Person;

import java.io.IOException;
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
public interface OrganizationDAO {
    public Organization getOrganization(String openCivicDataId);
    public Iterator<Organization> getOrganizationsByJurisdiction(String jurisdictionId);
    public Iterator<Person> getMembers(String openCivicDataId);
}
