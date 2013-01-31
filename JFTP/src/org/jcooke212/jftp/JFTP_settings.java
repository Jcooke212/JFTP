package org.jcooke212.jftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

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

/*****************************************************************************************************
 * The activity that manages the server connection settings
 * @author James Cooke
 *****************************************************************************************************/
public class JFTP_settings extends FragmentActivity implements ActionBar.TabListener 
{

	private ServerFragmentAdapter mSectionsPagerAdapter;
	private int currentTab;
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
	 * Define behavior for menu item clicks.
	 *************************************************************************************************/
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
	
	/*************************************************************************************************
	 *  Save the current tab to a file in private app data. (This is currently insecure)
	 *************************************************************************************************/
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
			e.printStackTrace();
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		spin = null;
		text = null;
	}

	/*************************************************************************************************
	 * Delete a file containing server info from the system.
	 * @param target name of the file to be deleted.
	 *************************************************************************************************/
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
		currentTab = tab.getPosition();
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
	{
		currentTab = tab.getPosition();
	}

}
