package org.opencivicdata.android.opencivicdata.dao.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.PaginatedList;
import org.opencivicdata.android.opencivicdata.exceptions.OpenCivicDataRetrievalException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
public abstract class GenericAPIPaginatedList<E> extends APIBase implements PaginatedList<E> {

    protected String method;
    protected String[] fields;
    protected Map<String, String> params;
    protected int page;
    protected int maxPage;

    public GenericAPIPaginatedList(String method, String[] fields, Map<String, String> params) {
        this.method = method;
        this.fields = fields;
        this.params = params;
        this.page = -1;
        this.maxPage = 0;
    }

    protected abstract E handleObject(JSONObject input);

    @Override
    public Iterator<E> getNextPage() {
        this.page = this.page + 1;
        this.params.put("page", String.valueOf(this.page));
        ArrayList<E> arrayList = new ArrayList<E>();

        try {
            JSONObject response = this.getObjectFor(this.method, this.fields, this.params);
            JSONObject meta = response.getJSONObject("meta");
            this.maxPage = meta.getInt("max_page");
            this.page = meta.getInt("page");

            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); ++i) {
                arrayList.add(this.handleObject(results.getJSONObject(i)));
            }

        } catch (IOException e) {
            throw new OpenCivicDataRetrievalException(e.getMessage());
        } catch (JSONException e) {
            throw new OpenCivicDataRetrievalException(e.getMessage());
        }

        return arrayList.iterator();
    }

    @Override
    public boolean hasNextPage() {
        return (this.page < this.maxPage);
    }
}
