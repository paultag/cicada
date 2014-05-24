package org.opencivicdata.android.opencivicdata.support;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import org.opencivicdata.android.opencivicdata.R;

public abstract class GenericActionBarActivity extends ActionBarActivity {

	protected GenericTabListener genericTabListener;
	protected GenericViewPager genericViewPager;
	protected ActionBar actionBar;

	/**
	 *
	 */
	public abstract void addTabs();

	protected void addTab(Fragment fragment, String tabName) {
		if (
			this.genericViewPager == null ||
			this.actionBar == null ||
		    this.genericTabListener == null
		) {
			throw new IllegalStateException("Attempted to addTab before onCreate");
		}

		this.genericViewPager.addFragment(fragment);
		ActionBar.Tab tab = this.actionBar.newTab().setText(tabName);
		tab.setTabListener(this.genericTabListener);
		this.actionBar.addTab(tab);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_pager);
		/* OK. Basic stuff is here. Let's do the stuff we need to do now. */

		/* Firstly, let's set up the Tab follower. */
		viewPager.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						getActionBar().setSelectedNavigationItem(position);
					}
				}
		);

		/* Great. Now, let's add a Generic fragment adapter */
		this.genericViewPager = new GenericViewPager(this.getSupportFragmentManager());
		viewPager.setAdapter(this.genericViewPager);

		/* And a listener to do the paging */
		GenericTabListener genericTabListener = new GenericTabListener(viewPager);
		this.genericTabListener = genericTabListener;

		this.actionBar = this.getSupportActionBar();
		this.actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		this.addTabs();
	}

}
