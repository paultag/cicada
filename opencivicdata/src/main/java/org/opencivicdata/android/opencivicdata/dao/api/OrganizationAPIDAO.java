package org.opencivicdata.android.opencivicdata.dao.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.OrganizationDAO;
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
    public Iterator<Organization> getOrganizationsByJurisdiction(String jurisdictionId) {
        ArrayList<Organization> arrayList = new ArrayList<Organization>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("jurisdiction_id", jurisdictionId);

        try {
            JSONObject directory = this.getObjectFor("organizations", null, params);
            JSONArray orgs = directory.getJSONArray("results");
            for (int i = 0; i < orgs.length(); ++i) {
                JSONObject org = orgs.getJSONObject(i);
                Organization orga = OrganizationAPIDAO.createOrganization(org);
                arrayList.add(orga);
            }
        } catch (IOException e) {
            Log.w("PAUL", e.toString());
            return null;
        } catch (JSONException e) {
            Log.w("PAUL", e.toString());
            return null;
        }

        return arrayList.iterator();
    }

    @Override
    public Iterator<Person> getMembers(String openCivicDataId) {
        ArrayList<Person> arrayList = new ArrayList<Person>();
        JSONArray memberships;

        Log.w("PAUL", "Loading memberships");

        try {
            Log.w("PAUL", "Loading object");
            JSONObject jsonOrganization = this.getObjectFor(openCivicDataId, null, null);
            Log.w("PAUL", "JSONing");
            memberships = jsonOrganization.getJSONArray("memberships");
            Log.w("PAUL", "Got Memberships");
        } catch (IOException e) {
            Log.w("PAUL", e.toString());
            return null;
        } catch (JSONException e) {
            Log.w("PAUL", e.toString());
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
                Log.w("PAUL", e.toString());
                /* TODO Error handle */
            }
        }
        Log.w("PAUL", "Loaded.");

        return arrayList.iterator();
    }
}