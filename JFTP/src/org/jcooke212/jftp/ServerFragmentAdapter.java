package org.jcooke212.jftp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*****************************************************************************************************
 * Handles the creation of Fragments for the tabs in JFTP_settings
 * @author James Cooke
 *****************************************************************************************************/
public class ServerFragmentAdapter extends FragmentPagerAdapter 
{
	private int count = 0;
	
	/*************************************************************************************************
	 * Do nothing then pass the fragment manager to the superclass' constructor
	 * @param fm Do nothing with this so pass it to the constructor for the super class
	 *************************************************************************************************/
	public ServerFragmentAdapter(FragmentManager fm)
	{
		super(fm);
	}

	/*************************************************************************************************
	 * Create an @ServersFragment to display and collect information about a remote server
	 *************************************************************************************************/
	@Override
	public Fragment getItem(int arg0) 
	{
		ServersFragment frag = new ServersFragment();
		Bundle args = new Bundle();
		args.putInt(ServersFragment.SERVER_NUMBER_STRING, count);
		frag.setArguments(args);
		count++;
		return frag;
	}

	/*************************************************************************************************
	 * Return the number of server fragments (This is limited to three for now)
	 *************************************************************************************************/
	@Override
	public int getCount() 
	{
		return 3;
	}

}
