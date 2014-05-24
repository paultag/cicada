package org.opencivicdata.android.opencivicdata.dao.api;

import org.opencivicdata.android.opencivicdata.dao.PersonDAO;
import org.opencivicdata.android.opencivicdata.dao.api.iterators.PersonAPIIterator;
import org.opencivicdata.android.opencivicdata.models.Person;

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
    public PersonAPIDAO() {

    }

    @Override
    public Person getPerson(String openCivicDataID) {
        return null;
    }

    @Override
    public Iterator<Person> getPeopleByJurisdiction(String jurisdictionId) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("jurisdiction_id", jurisdictionId);

        PersonAPIIterator personAPIIterator = new PersonAPIIterator("people", null, params);
        return personAPIIterator;
    }
}
