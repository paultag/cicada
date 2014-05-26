package org.opencivicdata.android.opencivicdata.dao.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.PersonDAO;
import org.opencivicdata.android.opencivicdata.dao.api.iterators.PersonAPIIterator;
import org.opencivicdata.android.opencivicdata.models.Organization;
import org.opencivicdata.android.opencivicdata.models.Person;

import java.io.IOException;
import java.util.HashMap;
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
public class PersonAPIDAO extends APIBase implements PersonDAO {

    public static Person createPerson(JSONObject jsonPerson) throws JSONException {
        Person person = new Person();
        person.setOpenCivicId(jsonPerson.getString("id"));
        person.setName(jsonPerson.getString("name"));
        person.setImage(jsonPerson.getString("image"));
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
    public Iterator<Organization> getOrganizations(String personOpenCivicId) {
        throw new IllegalStateException();
    }

}
