package org.jcooke212.jftp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ServerFragmentAdapter extends FragmentPagerAdapter 
{
	private int count = 0;
	public ServerFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) 
	{
		count = arg0;
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
