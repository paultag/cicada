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


public class MainActivity extends ActionBarActivity {

    private class PersonAdaptor extends BaseAdapter {
        Context context;

        PersonAdaptor(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {return null;}
        @Override
        public long getItemId(int i) {return 0;}

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout layout;

            if (view == null) {
                layout = (LinearLayout) LayoutInflater.from(this.context).inflate(R.layout.person_item, null);
            } else {
                layout = (LinearLayout) view;
            }

            /* Set imageView and friends */

            return layout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) this.findViewById(R.id.activity_main_list);
        lv.setAdapter(new PersonAdaptor(this));
    }

}
