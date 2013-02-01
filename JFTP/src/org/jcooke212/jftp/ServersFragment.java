package org.jcooke212.jftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/*****************************************************************************************************
 * Inflates the server_frag layout to collect and display server information
 * @author James Cooke
 *****************************************************************************************************/
public class ServersFragment extends Fragment implements OnClickListener 
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
		Button lockButton = (Button) myView.findViewById(R.id.btn_lock);
		lockButton.setOnClickListener(this);
		try 
		{
			StringBuffer fileContent = new StringBuffer("");
			@SuppressWarnings("unused")
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
	
	/*************************************************************************************************
	 * Load a file in the private data section of the app. 
	 * @param target The name of the file to be loaded. This should be "Server" plus this tabs 
	 * number.
	 * @param container The view this fragment is displaying.
	 *************************************************************************************************/
	private void loadFile(String target, View container)
	{
		String[] info = target.split(";");
		EditText et;
		// Set username.
		et = (EditText) container.findViewById(R.id.et_uname);
		et.setText(info[0]);
		et.setEnabled(false);
		// Set Ip Address.
		et = (EditText) container.findViewById(R.id.et_ipaddress);
		et.setText(info[1]);
		et.setEnabled(false);
		// Set port.
		et = (EditText) container.findViewById(R.id.et_port);
		et.setText(info[2]);
		et.setEnabled(false);
		// We are done with the EditText now.
		et = null;
		// Set connection type.
		Spinner spinner = (Spinner) container.findViewById(R.id.spn_connType);
		if(info[3].equals("FTP"))
		{
			spinner.setSelection(0);
		}
		else
		{
			spinner.setSelection(1);
		}
		
	}

	
	/**
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) 
	{
		saveCurrent((View) v.getParent());
	}

	/**
	 * 
	 * @param parent
	 */
	private void saveCurrent(View parent) 
	{
		EditText text;
		String serverInfo = new String();
		text = (EditText)parent.findViewById(R.id.et_uname);
		serverInfo += text.getText() + ";";
		text.setEnabled(false);
		text = (EditText)parent.findViewById(R.id.et_ipaddress);
		serverInfo += text.getText() + ";";
		text.setEnabled(false);
		text = (EditText)parent.findViewById(R.id.et_port);
		serverInfo += text.getText() + ";";
		text.setEnabled(false);
		Spinner spin = (Spinner)parent.findViewById(R.id.spn_connType);
		serverInfo += spin.getSelectedItem();
		spin.setEnabled(false);
		try 
		{
			Activity activity = getActivity();
			int currentTab = activity.getActionBar().getSelectedTab().getPosition();
			byte[] bArray = serverInfo.getBytes();
			FileOutputStream dataOut = activity.openFileOutput("Server" + currentTab, Context.MODE_PRIVATE);
			Toast.makeText(activity, "Server" + currentTab, Toast.LENGTH_SHORT).show();
			dataOut.write(bArray);
			dataOut.flush();
			dataOut.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		spin = null;
		text = null;
	}

}
