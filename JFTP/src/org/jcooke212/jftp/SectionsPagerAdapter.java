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
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = new LocalFragment();
		Bundle args = new Bundle();
		args.putInt(LocalFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() 
	{ 
		return 2;
	}
}