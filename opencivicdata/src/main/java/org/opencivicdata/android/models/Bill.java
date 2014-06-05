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
import java.util.List;

/**
 * Open Civic Data representation of a Bill
 *
 * http://docs.opencivicdata.org/en/latest/data/bill.html
 */
public class Bill {

    public static class Action {
        protected String description;

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }

    protected Organization organization;
    protected String name;
    protected String title;
    protected String type;
    protected String openCivicDataId;
    protected List<Action> actions;

    public List<Action> getActions() { return actions; }
    public void setActions(List<Action> actions) { this.actions = actions; }

    public void addAction(Action action) {
        if (this.actions == null) {
            this.actions = new ArrayList<Action>();
        }
        this.actions.add(action);
    }

    public String getId() {
        return openCivicDataId;
    }
    public void setId(String openCivicDataId) {
        this.openCivicDataId = openCivicDataId;
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
