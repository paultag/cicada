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

import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.dao.PersonDAO;
import org.opencivicdata.android.dao.api.paginators.APIPersonPaginator;
import org.opencivicdata.android.models.Person;

import java.io.IOException;
import java.util.HashMap;


/**
 * Implementation of the PersonDAO, using the Open Civic Data API
 * as the Data backend.
 */
public class PersonAPIDAO extends APIBase implements PersonDAO {

    /**
     * Internal implementation detail for how we turn a JSON API response
     * into a Person.
     *
     * @param jsonPerson JSON API response of a Person
     * @return a Person object, hydrated.
     * @throws JSONException On malformed JSON
     */
    public static Person createPerson(JSONObject jsonPerson) throws JSONException {
        Person person = new Person();
        person.setOpenCivicId(jsonPerson.getString("id"));
        person.setName(jsonPerson.getString("name"));
        person.setImage(jsonPerson.optString("image", null));
        return person;
    }

    @Override
    public Person getPerson(String openCivicDataID) {
        JSONObject jsonPerson = null;
        try {
            jsonPerson = this.getObjectFor(openCivicDataID, null, null);
            return PersonAPIDAO.createPerson(jsonPerson);
        } catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public PaginatedList<Person> getPeopleByName(String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        return new APIPersonPaginator("people", null, params);
    }

}
