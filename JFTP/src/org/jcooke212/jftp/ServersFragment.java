package org.jcooke212.jftp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*****************************************************************************************************
 * Inflates the server_frag layout to collect and display server information
 * @author James Cooke
 *****************************************************************************************************/
public class ServersFragment extends Fragment
{

	/*************************************************************************************************
	 * Use the information in server_frag.xml to set up this fragments view
	 *************************************************************************************************/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View myView = inflater.inflate(R.layout.server_frag, container, false);
		return myView;
	}

}
