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

import android.text.TextUtils;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.exceptions.OpenCivicDataException;
import org.opencivicdata.android.support.HttpManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Common API base, provides methods to aid in working with the OpenCivicData API's
 * endpoints in a normal way.
 */
public class APIBase {
    private static final String TAG = APIBase.class.getName();

    protected static String apiKey;
    protected static String host;

    public APIBase() {
        HttpManager.init();
    }

    public static void setApiKey(String apiKey) {
        APIBase.apiKey = apiKey;
    }

    public static void setApiHost(String host) {
        APIBase.host = host;
    }

    /**
     * Gets the JSON Object for a given call
     *
     * @param method Open Civic Data endpoint method (/people, /organizations,
     *               or /jurisdictions)
     * @param fields Fields to request. May be null.
     * @param params Key/Value store of URL GET arguments.
     * @return a JSON object for the call
     * @throws IOException On bad I/O
     * @throws JSONException on bad JSON
     * @throws OpenCivicDataException when it's not set up right
     */
    protected JSONObject getObjectFor(
            String method,
            String[] fields,
            Map<String, String> params
    ) throws IOException, JSONException {
        return this.getJSONObject(this.getUrlFor(method, fields, params));
    }

    /**
     * Get a URL for the requested query.
     *
     * @param method Method to invoke, something like '/people',
     *               '/organizations', or '/jurisdictions'
     * @param fields Fields to request. May be null.
     * @param params Key/Value store of URL GET arguments.
     * @return String representing the URL matching the query.
     * @throws UnsupportedEncodingException if your encoding sucks.
     */
    protected String getUrlFor(
            String method,
            String[] fields,
            Map<String,String> params
    ) throws UnsupportedEncodingException {
        if (APIBase.apiKey == null || APIBase.host == null) {
            throw new OpenCivicDataException("API key or API host not configured");
        }

        if (fields != null && fields.length > 0) {
            params.put("fields", TextUtils.join(",", fields));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?apikey=" + APIBase.apiKey);

        if (params != null) {
            for (Map.Entry<String, String> i : params.entrySet()) {
                stringBuilder.append("&");
                stringBuilder.append(URLEncoder.encode(i.getKey(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(i.getValue(), "UTF-8"));
            }
        }

        return APIBase.host + "/" + method + "/" + stringBuilder.toString();
    }

    /**
     * Get a JSONObject for a given API URL.
     *
     * @param url URL to fetch
     * @return JSONObject that matches the URL
     * @throws IOException
     * @throws JSONException
     */
    protected JSONObject getJSONObject(String url) throws IOException, JSONException {
        Log.d(this.TAG, "Loading JSON Object: " + url);
        return new JSONObject(this.fetchJSON(url));
    }

    /**
     * Fetch the JSON (in String format) for a given URL.
     *
     * @param requestedUrl URL to fetch
     * @return String of JSON data
     * @throws IOException
     */
    protected String fetchJSON(String requestedUrl) throws IOException {
        Log.d(this.TAG, "Hitting URL " + requestedUrl);
        URL url = new URL(requestedUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /* TODO: Set connection headers (User-agent, etc) */

        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new IOException("Bad URL: " + requestedUrl);
        }

        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        connection.disconnect();
        return stringBuilder.toString();
    }

}
