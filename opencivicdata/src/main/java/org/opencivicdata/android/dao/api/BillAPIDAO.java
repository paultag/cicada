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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.dao.BillDAO;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.exceptions.OpenCivicDataRetrievalException;
import org.opencivicdata.android.models.Bill;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BillAPIDAO extends APIBase implements BillDAO {

    /**
     * Paginator implementation for the Open Civic Data API.
     *
     * This lets the UI properly handle lists of unknown or extremely long length,
     * allowing for loading of data progressively.
     *
     * This is for pages of Bills.
     */
    public class APIBillPaginator extends GenericAPIPaginatedList<Bill> {
        public APIBillPaginator(String method, String[] fields, Map<String, String> params) {
            super(method, fields, params);
        }

        @Override
        protected Bill handleObject(JSONObject input) {
            try {
                return BillAPIDAO.createBill(input);
            } catch (JSONException e) {
                throw new OpenCivicDataRetrievalException(
                        "Can't hydrate Bill: " + e.getMessage());
            }
        }
    }

    public static Bill createBill(JSONObject jsonBill) throws JSONException {
        Bill bill = new Bill();
        bill.setName(jsonBill.getString("name"));
        bill.setTitle(jsonBill.getString("title"));
        bill.setId(jsonBill.getString("id"));

        JSONArray actions = jsonBill.optJSONArray("actions");

        if (actions != null) {
            for (int i = 0; i < actions.length(); ++i) {
                Bill.Action action = new Bill.Action();
                JSONObject aobj = actions.getJSONObject(i);
                action.setDescription(aobj.getString("description"));
                bill.addAction(action);
            }
        }

        return bill;
    }

    @Override
    public Bill getBillByOpenCivicDataID(String openCivicDataId) {
        JSONObject jsonPerson;
        try {
            jsonPerson = this.getObjectFor(openCivicDataId, null, null);
            return BillAPIDAO.createBill(jsonPerson);
        } catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public PaginatedList<Bill> getBillsByTitle(String title) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("title", title);
        return new APIBillPaginator("bills", null, params);
    }

    @Override
    public PaginatedList<Bill> getBillsByName(String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        return new APIBillPaginator("bills", null, params);
    }
}
