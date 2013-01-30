package org.jcooke212.jftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class JFTP_settings extends FragmentActivity implements ActionBar.TabListener 
{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	ServerFragmentAdapter mSectionsPagerAdapter;
	private int currentTab;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jftp_settings);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new ServerFragmentAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager2);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jftp_settings, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.del_server:
				deleteCurrent("Server" + currentTab);
				return true;
			case R.id.save_server:
				saveCurrent();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	/**
	 *  Save the current tab
	 */
	private void saveCurrent() 
	{
		EditText text;
		String serverInfo = new String();
		text = (EditText)findViewById(R.id.uname);
		serverInfo += text.getText() + ";";
		text = (EditText)findViewById(R.id.ip_address);
		serverInfo += text.getText() + ";";
		text = (EditText)findViewById(R.id.port);
		serverInfo += text.getText() + ";";
		Spinner spin = (Spinner)findViewById(R.id.connType);
		serverInfo += spin.getSelectedItem();
		try 
		{
			byte[] bArray = serverInfo.getBytes();
			FileOutputStream dataOut = openFileOutput("Server" + currentTab, Context.MODE_PRIVATE);
			Toast.makeText(this, "Server" + currentTab, Toast.LENGTH_SHORT).show();
			dataOut.write(bArray);
			dataOut.flush();
			dataOut.close();
		} 
		catch (Exception e) 
		{
			Toast.makeText(this, "Problem saving file.", Toast.LENGTH_SHORT).show();
		}
		spin = null;
		text = null;
	}

	private void deleteCurrent(String target) 
	{
		File appStorage = this.getFilesDir();
		File[] files = appStorage.listFiles();
		try 
		{
			for(File file: files)
			{
				if(file.getName().equals(target))
				{
					file.delete();
				}
			}
		} 
		catch (Exception ex) 
		{
			// TODO Auto-generated catch block
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
		} 

		
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		currentTab = tab.getPosition();
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{
		currentTab = tab.getPosition();
	}

}
