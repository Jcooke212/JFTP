package org.jcooke212.jftp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*****************************************************************************************************
 * Handles the creation of Fragments for the tabs in JFTP
 * @author James Cooke
 *****************************************************************************************************/
public class SectionsPagerAdapter extends FragmentPagerAdapter 
{
	/*************************************************************************************************
	 * Do nothing then pass the fragment manager to the superclass' constructor  
	 * @param fm Do nothing with this so pass it to the constructor for the super class
	 *************************************************************************************************/
	public SectionsPagerAdapter(FragmentManager fm) 
	{
		super(fm);
	}

	/*************************************************************************************************
	 * Create an @FSListFrag and give it an argument telling it where to get the information it will
	 * display
	 *************************************************************************************************/
	@Override
	public Fragment getItem(int position) 
	{
		Fragment fragment = new FSListFrag();
		Bundle args = new Bundle();
		if(position == 0)
		{
		    args.putString(FSListFrag.ARG_DISPLAY_TYPE, "display_local");
		}
		else
		{
		    args.putString(FSListFrag.ARG_DISPLAY_TYPE, "display_remote");
		}
		fragment.setArguments(args);
		return fragment;
	}

	/*************************************************************************************************
	 * Return how many fragments have been created. (For this section there will always be two 
	 * fragments 
	 *************************************************************************************************/
	@Override
	public int getCount() 
	{ 
		return 2;
	}
}