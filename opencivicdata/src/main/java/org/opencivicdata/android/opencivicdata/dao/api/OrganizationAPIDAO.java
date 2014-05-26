package org.opencivicdata.android.opencivicdata.dao.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.OrganizationDAO;
import org.opencivicdata.android.opencivicdata.dao.PaginatedList;
import org.opencivicdata.android.opencivicdata.dao.api.paginators.APIOrganizationPaginator;
import org.opencivicdata.android.opencivicdata.models.Organization;
import org.opencivicdata.android.opencivicdata.models.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */
public class OrganizationAPIDAO extends APIBase implements OrganizationDAO {

    public static Organization createOrganization(JSONObject jsonOrganization) throws JSONException {
        Organization organization = new Organization();
        organization.setOpenCivicId(jsonOrganization.getString("id"));
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
            Log.w("PAUL", e.toString());
            return null;
        } catch (JSONException e) {
            Log.w("PAUL", e.toString());
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