package org.opencivicdata.android.dao.api;

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

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.dao.OrganizationDAO;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.dao.api.paginators.APIOrganizationPaginator;
import org.opencivicdata.android.models.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the OrganizationDAO, using the Open Civic Data API
 * as the Data backend.
 */
public class OrganizationAPIDAO extends APIBase implements OrganizationDAO {

    private static final String TAG = APIBase.class.getName();

    /**
     * Internal implementation detail for how we turn a JSON API response
     * into an Organization.
     *
     * @param jsonOrganization JSON API Response of an Organization
     * @return a Hydrated Organization object
     * @throws JSONException on malformed JSON
     */
    public static Organization createOrganization(JSONObject jsonOrganization) throws JSONException {
        Organization organization = new Organization();
        organization.setId(jsonOrganization.getString("id"));
        organization.setName(jsonOrganization.getString("name"));
        return organization;
    }

    @Override
    public Organization getOrganization(String openCivicDataId) {
        JSONObject jsonOrganization = null;
        try {
            jsonOrganization = this.getObjectFor(openCivicDataId, null, null);
            return OrganizationAPIDAO.createOrganization(jsonOrganization);
        } catch (IOException e) {
            Log.w(this.TAG, e.toString());
            return null;
        } catch (JSONException e) {
            Log.w(this.TAG, e.toString());
            return null;
        }
    }

    @Override
    public PaginatedList<Organization> getOrganizationsByJurisdiction(String jurisdictionId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("jurisdiction_id", jurisdictionId);
        return new APIOrganizationPaginator("organizations", null, params);
    }
}