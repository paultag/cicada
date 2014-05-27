package org.opencivicdata.android.dao.api.paginators;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.dao.api.GenericAPIPaginatedList;
import org.opencivicdata.android.dao.api.OrganizationAPIDAO;
import org.opencivicdata.android.exceptions.OpenCivicDataRetrievalException;
import org.opencivicdata.android.models.Organization;

import java.util.Map;

public class APIOrganizationPaginator extends GenericAPIPaginatedList<Organization> {
    public APIOrganizationPaginator(String method, String[] fields, Map<String, String> params) {
        super(method, fields, params);
    }

    @Override
    protected Organization handleObject(JSONObject input) {
        try {
            return OrganizationAPIDAO.createOrganization(input);
        } catch (JSONException e) {
            throw new OpenCivicDataRetrievalException(
                    "Can't hydrate Organization: " + e.getMessage());
        }
    }
}
