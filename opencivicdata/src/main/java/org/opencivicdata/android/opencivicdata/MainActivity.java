package org.opencivicdata.android.opencivicdata;

/**
 * Main Activity, the entrypoint when first launched.
 */

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.opencivicdata.android.opencivicdata.adaptors.PersonAdaptor;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) this.findViewById(R.id.activity_main_list);
        lv.setAdapter(new PersonAdaptor(this));
    }

}
