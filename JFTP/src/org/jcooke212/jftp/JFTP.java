package org.jcooke212.jftp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class JFTP extends FragmentActivity implements ActionBar.TabListener 
{
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	/*************************************************************************************************
	 * Add two tabs containing @FSListFrag to the action bar.
	 *************************************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jftp);
		// Set up the action bar. ********************************************************************
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Set up the ViewPager with the sections adapter. *******************************************
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		// When swiping between different sections, select the corresponding tab. ********************
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() 
		{
			@Override
			public void onPageSelected(int position) 
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});
		// Add two tabs for the local and remote FileSystems. ****************************************
		actionBar.addTab(actionBar.newTab().setText("Local").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Remote").setTabListener(this));
	}

	/*************************************************************************************************
	 * Inflate the options menu from XML for this activity. 
	 *************************************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jftp, menu);
		return true;
	}
	
	/*************************************************************************************************
	 * Define the behavior for a menu item selection.
	 *************************************************************************************************/
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.menu_settings:
				startSettings();
				return true;
			default:
				return super.onOptionsItemSelected(item);	
		}
	}
	
	/*************************************************************************************************
	 * Start the JFTP_settings activity 
	 *************************************************************************************************/
	private void startSettings()
	{
		Intent iSettings = new Intent(this, JFTP_settings.class);
		startActivity(iSettings);
	}
	
	/*************************************************************************************************
	 * When a tab is selected set it as the current item.
	 *************************************************************************************************/
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	/*************************************************************************************************
	 * Do nothing when a tab is unselected
	 *************************************************************************************************/
	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{}

	/*************************************************************************************************
	 * Do nothing when tab is reselected
	 *************************************************************************************************/
	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{}

}
