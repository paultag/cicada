package org.opencivicdata.android.opencivicdata.dao.api;

import android.text.TextUtils;


import org.json.JSONException;
import org.json.JSONObject;
import org.opencivicdata.android.opencivicdata.support.HttpManager;

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
 * This file is a part of Open Civic Data.
 * <p/>
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions of the BSD-3 license in
 * the LICENSE file contained in this source distribution.
 * <p/>
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */
public class APIBase {
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

    protected JSONObject getObjectFor(
            String method,
            String[] fields,
            Map<String, String> params
    ) throws IOException, JSONException {
        return this.getJSONObject(this.getUrlFor(method, fields, params));
    }

    protected String getUrlFor(
            String method,
            String[] fields,
            Map<String,String> params
    ) throws UnsupportedEncodingException {
        if (APIBase.apiKey == null || APIBase.host == null) {
            throw new RuntimeException("API key or API host not configured");
        }

        if (fields != null && fields.length > 0) {
            params.put("fields", TextUtils.join(",", fields));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?api_key=" + APIBase.apiKey);

        if (params != null) {
            for (Map.Entry<String, String> i : params.entrySet()) {
                stringBuilder.append("&");
                stringBuilder.append(URLEncoder.encode(i.getKey(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(i.getValue(), "UTF-8"));
            }
        }

        return APIBase.host + "/" + method + stringBuilder.toString();
    }

    protected JSONObject getJSONObject(String url) throws IOException, JSONException {
        return new JSONObject(this.fetchJSON(url));
    }

    protected String fetchJSON(String requestedUrl) throws IOException {
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
