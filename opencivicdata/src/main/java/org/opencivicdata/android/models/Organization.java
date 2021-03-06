package org.opencivicdata.android.models;

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

import java.util.ArrayList;

/**
 * Open Civic Data representation of a Organization
 *
 * http://docs.opencivicdata.org/en/latest/data/organization.html
 */
public class Organization {
    protected String openCivicId;
    protected String name;
    protected ArrayList<Person> members;

    public String getId() {
        return openCivicId;
    }
    public void setId(String openCivicId) {
        this.openCivicId = openCivicId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }
    public void setMembers(ArrayList<Person> members) {
        this.members = members;
    }
}
