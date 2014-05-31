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
import org.opencivicdata.android.dao.BillDAO;
import org.opencivicdata.android.dao.PaginatedList;
import org.opencivicdata.android.dao.api.paginators.APIBillPaginator;
import org.opencivicdata.android.dao.api.paginators.APIPersonPaginator;
import org.opencivicdata.android.models.Bill;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
public class BillAPIDAO extends APIBase implements BillDAO {

    public static Bill createBill(JSONObject jsonBill) throws JSONException {
        Bill bill = new Bill();
        bill.setName(jsonBill.getString("name"));
        bill.setTitle(jsonBill.getString("title"));
        return bill;
    }

    @Override
    public Bill getBillByOpenCivicDataID(String openCivicDataId) {
        JSONObject jsonPerson = null;
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
