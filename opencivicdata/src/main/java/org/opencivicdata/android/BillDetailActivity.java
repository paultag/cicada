package org.opencivicdata.android;

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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import org.opencivicdata.android.tasks.BillDetailActivityPopulator;

import java.util.Random;

/**
 *
 */
public class BillDetailActivity extends Activity {

    public static final String OPEN_CIVIC_DATA_ID = "org.opencivicdata.android.BillDetailActivity.OPEN_CIVIC_DATA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.loading);
        Intent intent = this.getIntent();
        String openCivicDataId = intent.getStringExtra(BillDetailActivity.OPEN_CIVIC_DATA_ID);
        BillDetailActivityPopulator billDetailActivityPopulator = new BillDetailActivityPopulator(this);
        billDetailActivityPopulator.execute(openCivicDataId);

        TextView textView = (TextView) this.findViewById(R.id.loading_text);
        String[] loadingMessages = this.getResources().getStringArray(R.array.loading_messages);
        String loadingMessage = loadingMessages[new Random().nextInt(loadingMessages.length)];
        textView.setText(loadingMessage);
    }
}
