package org.jcooke212.jftp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

/*****************************************************************************************************
 * The activity that manages the server connection settings
 * @author James Cooke
 *****************************************************************************************************/
public class JFTP_settings extends FragmentActivity implements ActionBar.TabListener 
{

	private ServerFragmentAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	/*************************************************************************************************
	 * Add three server tabs to the @ActionBar for this activity.
	 *************************************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jftp_settings);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the app's primary sections.
		mSectionsPagerAdapter = new ServerFragmentAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager2);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() 
		{
			@Override
			public void onPageSelected(int position) 
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});
		actionBar.addTab(actionBar.newTab().setText("Server 1").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Server 2").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Server 3").setTabListener(this));
	}

	/*************************************************************************************************
	 * Create the options menu from XML.
	 *************************************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jftp_settings, menu);
		return true;
	}

	/*************************************************************************************************
	 * When a tab is selected set it as the current item. And track the current tab with the 
	 * currentTab variable.
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
	 * When a tab is reselected update the current tab variable. 
	 *************************************************************************************************/
	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{}

}
