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
import org.opencivicdata.android.dao.api.PersonAPIDAO;
import org.opencivicdata.android.exceptions.OpenCivicDataRetrievalException;
import org.opencivicdata.android.models.Person;

import java.util.Map;

public class APIPersonPaginator extends GenericAPIPaginatedList<Person> {
    public APIPersonPaginator(String method, String[] fields, Map<String, String> params) {
        super(method, fields, params);
    }

    @Override
    protected Person handleObject(JSONObject input) {
        try {
            return PersonAPIDAO.createPerson(input);
        } catch (JSONException e) {
            throw new OpenCivicDataRetrievalException(
                    "Can't hydrate Person: " + e.getMessage());
        }
    }
}