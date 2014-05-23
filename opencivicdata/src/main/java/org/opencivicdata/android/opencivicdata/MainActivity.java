package org.opencivicdata.android.opencivicdata;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.adaptors.PersonAdaptor;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

}
