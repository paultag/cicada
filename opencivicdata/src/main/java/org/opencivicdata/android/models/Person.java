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

/**
 * Open Civic Data representation of a Person
 *
 * http://docs.opencivicdata.org/en/latest/data/person.html
 */
public class Person {
    protected String openCivicId;
    protected String name;
    protected String image;

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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
