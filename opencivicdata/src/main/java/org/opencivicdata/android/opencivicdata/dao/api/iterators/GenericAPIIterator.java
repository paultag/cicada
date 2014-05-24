package org.opencivicdata.android.opencivicdata.dao.api.iterators;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.dao.api.APIBase;

import java.io.IOException;
import java.util.ArrayDeque;
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
public class GenericAPIIterator extends APIBase implements Iterator<JSONObject> {
    private String method;
    private String[] fields;
    private Map<String, String> params;
    private ArrayDeque<JSONObject> queue;
    private int page;
    private int max_page;

    public GenericAPIIterator(String method, String[] fields, Map<String, String> params) {
        this.method = method;
        this.fields = fields;
        this.params = params;
        this.queue = new ArrayDeque<JSONObject>();
        this.max_page = 0;
        this.page = -1;
    }

    protected void handleResponse(JSONObject response) throws JSONException {
        JSONObject meta = response.getJSONObject("meta");
        this.page = meta.getInt("page");
        this.max_page = meta.getInt("max_page");
        JSONArray results = response.getJSONArray("response");

        for (int i = 0; i < results.length(); ++i) {
            this.queue.add(results.getJSONObject(i));
        }
    }

    public void fetchNextPage() throws IOException, JSONException {
        if (this.page >= this.max_page) {
            throw new IllegalStateException("Trying to go past the last page.");
        }

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.putAll(this.params);
        queryParams.put("page", String.valueOf(this.page));
        JSONObject response = this.getObjectFor(this.method, this.fields, this.params);
        this.handleResponse(response);
    }

    @Override
    public boolean hasNext() {
        if (! this.queue.isEmpty()) {
            return true;
        }
        if (this.page < this.max_page) {
            return true;
        }
        return false;
    }

    @Override
    public JSONObject next() {
        if (this.queue.isEmpty()) {
            try {
                this.fetchNextPage();
            } catch (IOException e) {
                throw new IllegalStateException("OMGWTFBBQ");
            } catch (JSONException e) {
                throw new IllegalStateException("OMGWTFBBQ");
            }
        }
        return this.queue.remove();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
