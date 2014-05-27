package org.opencivicdata.android.opencivicdata.support;

/*
 * This file is a part of Open Civic Data.
 *
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions
 * of the BSD-3 license in the LICENSE file contained in this source
 * distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 * - Eric Mill <konklone@gmail.com>
 */

import com.squareup.okhttp.OkHttpClient;

import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;

public class HttpManager {

    public static boolean initialized = false;

    public static void init() {
        if (HttpManager.initialized) {
            return;
        }
        HttpManager.initialized = true;

        OkHttpClient okHttpClient = new OkHttpClient();

        // adapted from https://github.com/mapbox/mapbox-android-sdk/pull/244
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }

        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        URL.setURLStreamHandlerFactory(okHttpClient);
    }
}
