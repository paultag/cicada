package org.opencivicdata.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.opencivicdata.android.dao.BillDAO;
import org.opencivicdata.android.dao.api.BillAPIDAO;
import org.opencivicdata.android.models.Bill;
import org.opencivicdata.android.tasks.BillDetailActivityPopulator;

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
        textView.setText("Waiting for the right radio waves...");
    }
}
