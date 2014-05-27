package org.opencivicdata.android.dao;

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

import org.opencivicdata.android.models.Person;

/**
 * The Person Data Access Object contains common and generic
 * methods to handle the retrieval of data from a generic
 * backend.
 *
 * Any implementation of a Person backend should implement
 * a DAO to interface to the storage backend.
 */
public interface PersonDAO {

    /**
     * Get a person by their Open Civic Data ID.
     *
     * @param openCivicDataID OCD ID for the person in question. Usually
     *                        takes the form of ocd-person/${UUID}
     * @return Person object if the Person has been found, otherwise this
     *                method shall return a null.
     */
    public Person getPerson(String openCivicDataID);

    /**
     * Search for all people partially matching a name `name`.
     *
     * @param name name to search the backend for.
     * @return a PaginatedList of Persons that fit the criteria.
     */
    public PaginatedList<Person> getPeopleByName(String name);
}
