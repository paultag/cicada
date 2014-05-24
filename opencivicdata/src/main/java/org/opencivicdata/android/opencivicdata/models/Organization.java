package org.opencivicdata.android.opencivicdata.models;

import java.util.ArrayList;

/**
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */
public class Organization {
    protected String openCivicId;
    protected String name;
    protected ArrayList<Person> members;

    public String getOpenCivicId() {
        return openCivicId;
    }

    public void setOpenCivicId(String openCivicId) {
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
