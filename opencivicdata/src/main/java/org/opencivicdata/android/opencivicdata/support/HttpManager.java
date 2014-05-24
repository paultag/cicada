package org.opencivicdata.android.opencivicdata.support;

import com.squareup.okhttp.OkHttpClient;

import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;

/**
 * Singleton class to manage HTTP interaction between OkHttp and HttpUrlConnection.
 *
 * Copyright (c) Eric Mill <konklone@gmail.com>, 2014, BSD-3
 */
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
