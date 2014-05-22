package org.opencivicdata.android.opencivicdata.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import org.opencivicdata.android.opencivicdata.R;


public class PersonAdaptor extends BaseAdapter {
    Context context;

    public PersonAdaptor(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

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
