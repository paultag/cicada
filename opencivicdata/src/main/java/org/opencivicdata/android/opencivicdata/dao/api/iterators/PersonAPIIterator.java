package org.opencivicdata.android.opencivicdata.dao.api.iterators;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.api.PersonAPIDAO;
import org.opencivicdata.android.opencivicdata.models.Person;

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
public class PersonAPIIterator implements Iterator<Person> {

    private GenericAPIIterator genericAPIIterator;

    public PersonAPIIterator(String method, String[] fields, Map<String, String> params) {
        this.genericAPIIterator = new GenericAPIIterator(method, fields, params);
    }

    @Override
    public boolean hasNext() {
        return this.genericAPIIterator.hasNext();
    }

    @Override
    public Person next() {
        JSONObject jsonPerson = this.genericAPIIterator.next();
        try {
            return PersonAPIDAO.createPerson(jsonPerson);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}