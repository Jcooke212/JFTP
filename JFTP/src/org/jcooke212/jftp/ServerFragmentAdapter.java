package org.jcooke212.jftp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ServerFragmentAdapter extends FragmentPagerAdapter 
{
	private int count = 3;
	public ServerFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) 
	{
		ServersFragment frag = new ServersFragment();
		return frag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
