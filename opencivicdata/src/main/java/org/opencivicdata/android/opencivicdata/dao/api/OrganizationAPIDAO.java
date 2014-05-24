package org.opencivicdata.android.opencivicdata.dao.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.OrganizationDAO;
import org.opencivicdata.android.opencivicdata.models.Organization;
import org.opencivicdata.android.opencivicdata.models.Person;

import java.io.IOException;
import java.util.ArrayList;
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
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public Iterator<Organization> getOrganizationsByJurisdiction(String jurisdictionId) {
        return null;
    }

    @Override
    public Iterator<Person> getMembers(String openCivicDataId) {
        ArrayList<Person> arrayList = new ArrayList<Person>();
        JSONArray memberships;

        try {
            JSONObject jsonOrganization = this.getObjectFor(openCivicDataId, null, null);
            memberships = jsonOrganization.getJSONArray("memberships");
        } catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }

        for (int i = 0; i < memberships.length(); ++i) {
            try {
                JSONObject jsonPerson = memberships.getJSONObject(i);
                if (jsonPerson.getString("person_id") != null) {
                    jsonPerson = jsonPerson.getJSONObject("person");
                    arrayList.add(PersonAPIDAO.createPerson(jsonPerson));
                }
            } catch (JSONException e) {
                /* TODO Error handle */
            }
        }

        return arrayList.iterator();
    }
}