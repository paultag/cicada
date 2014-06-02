package org.opencivicdata.android.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.opencivicdata.android.R;
import org.opencivicdata.android.dao.BillDAO;
import org.opencivicdata.android.dao.api.BillAPIDAO;
import org.opencivicdata.android.models.Bill;


public class BillDetailActivityPopulator extends AsyncTask<String, Void, Bill> {
    Activity activity;

    public BillDetailActivityPopulator(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Bill doInBackground(String... strings) {
        BillDAO billDAO = new BillAPIDAO();
        String string = strings[0];
        Bill bill = billDAO.getBillByOpenCivicDataID(string);
        return bill;
    }

    @Override
    protected void onPostExecute(Bill bill) {
        super.onPostExecute(bill);
        this.activity.setContentView(R.layout.bill_detail);

        TextView name = (TextView) this.activity.findViewById(R.id.bill_detail_name);
        TextView title = (TextView) this.activity.findViewById(R.id.bill_detail_title);

        name.setText(bill.getName());
        title.setText(bill.getTitle());
    }
}
