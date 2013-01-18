package org.jcooke212.jftp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter 
{

	public SectionsPagerAdapter(FragmentManager fm) 
	{
		super(fm);
	}

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

	@Override
	public int getCount() 
	{ 
		return 2;
	}
}