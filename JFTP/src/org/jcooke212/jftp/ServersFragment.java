package org.jcooke212.jftp;

import java.io.FileInputStream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

/*****************************************************************************************************
 * Inflates the server_frag layout to collect and display server information
 * @author James Cooke
 *****************************************************************************************************/
public class ServersFragment extends Fragment
{
	final public static String SERVER_NUMBER_STRING = "server_number";
	/*************************************************************************************************
	 * Use the information in server_frag.xml to set up this fragments view
	 *************************************************************************************************/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		int tabNumber = getArguments().getInt(SERVER_NUMBER_STRING);
		View myView = inflater.inflate(R.layout.server_frag, container, false);
		try 
		{
			StringBuffer fileContent = new StringBuffer("");
			int length;
			FileInputStream dataIn = getActivity().openFileInput("Server" + tabNumber);
			byte[] bArray = new byte[64];
			while ((length = dataIn.read(bArray)) != -1) 
			{
			    fileContent.append(new String(bArray));
			}
			loadFile(fileContent.toString(), myView);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return myView;
	}
	private void loadFile(String target, View container)
	{
		String[] info = target.split(";");
		EditText et;
		// Set username.
		et = (EditText) container.findViewById(R.id.uname);
		et.setText(info[0]);
		et.setEnabled(false);
		// Set Ip Address.
		et = (EditText) container.findViewById(R.id.ip_address);
		et.setText(info[1]);
		et.setEnabled(false);
		// Set port.
		et = (EditText) container.findViewById(R.id.port);
		et.setText(info[2]);
		et.setEnabled(false);
		// We are done with the EditText now.
		et = null;
		// Set connection type.
		Spinner spinner = (Spinner) container.findViewById(R.id.connType);
		if(info[3].equals("FTP"))
		{
			spinner.setSelection(0);
		}
		else
		{
			spinner.setSelection(1);
		}
		
	}

}
