package org.jcooke212.jftp;

import java.io.File;
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
	final public static String SERVER_FRAG_DELIMETER = ";";
	
	/*************************************************************************************************
	 * Use the information in server_frag.xml to set up this fragments view and load the file 
	 * associated with this fragment if it exists.
	 *************************************************************************************************/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		int tabNumber = getArguments().getInt(SERVER_NUMBER_STRING);
		View myView = inflater.inflate(R.layout.server_frag, container, false);
		Button button = (Button) myView.findViewById(R.id.btn_lock);
		button.setOnClickListener(this);
		button = (Button) myView.findViewById(R.id.btn_delete);
		button.setOnClickListener(this);
		try 
		{
			StringBuffer fileContent = new StringBuffer("");
			@SuppressWarnings("unused")
			int length;
			FileInputStream dataIn = getActivity().openFileInput("Server" + tabNumber);
			byte[] bArray = new byte[15];
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
		String[] info = target.split(SERVER_FRAG_DELIMETER);
		EditText et;
		et = (EditText) container.findViewById(R.id.et_uname);
		et.setEnabled(false);
		et.setText(info[0]);
		et = (EditText) container.findViewById(R.id.et_ipaddress);
		et.setEnabled(false);
		et.setText(info[1]);
		et = (EditText) container.findViewById(R.id.et_port);
		et.setEnabled(false);
		et.setText(info[2]);
		Spinner spinner = (Spinner) container.findViewById(R.id.spn_connType);
		if(info[3].equals("FTP"))
		{
			spinner.setSelection(0);
		}
		else
		{
			spinner.setSelection(1);
		}
		spinner.setEnabled(false);
	}

	
	/*************************************************************************************************
	 * If delete button is clicked delete the file and unlock form fields.
	 * @param v The view containing the EditTexts to unlock.
	 *************************************************************************************************/
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.btn_delete:
				this.deleteCurrent((View) v.getParent().getParent());
				break;
			case R.id.btn_lock:
				v.setEnabled(false);
				this.saveCurrent((View) v.getParent().getParent());
				break;
		}
	}

	/*************************************************************************************************
	 * Save the information in the Fragment to the appropriate file and lock its form fields.
	 * @param parent View with the form fields to be locked.
	 *************************************************************************************************/
	private void saveCurrent(View parent) 
	{
		EditText text;
		String serverInfo = new String("");
		text = (EditText)parent.findViewById(R.id.et_uname);
		text.setEnabled(false);
		serverInfo += text.getText() + SERVER_FRAG_DELIMETER;
		text = (EditText)parent.findViewById(R.id.et_ipaddress);
		text.setEnabled(false);
		serverInfo += text.getText() + SERVER_FRAG_DELIMETER;
		text = (EditText)parent.findViewById(R.id.et_port);
		text.setEnabled(false);
		serverInfo += text.getText() + SERVER_FRAG_DELIMETER;
		Spinner spin = (Spinner)parent.findViewById(R.id.spn_connType);
		spin.setEnabled(false);
		serverInfo += spin.getSelectedItem();
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
	}


	/*************************************************************************************************
	 * Delete a file containing server info from the system and re-enable form fields of this 
	 * fragment.
	 * @param target The file inside the private storage directory that should be deleted.
	 *************************************************************************************************/
	private void deleteCurrent(View parent) 
	{
		int currentTab = getActivity().getActionBar().getSelectedNavigationIndex();
		String target = new String("Server" + currentTab);
		File appStorage = getActivity().getFilesDir();
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
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		} 
		finally
		{
			EditText text;
			text = (EditText)parent.findViewById(R.id.et_uname);
			text.setEnabled(true);
			text.setText("");
			text = (EditText)parent.findViewById(R.id.et_ipaddress);
			text.setEnabled(true);
			text.setText("");
			text = (EditText)parent.findViewById(R.id.et_port);
			text.setEnabled(true);
			text.setText("");
			Spinner spin = (Spinner)parent.findViewById(R.id.spn_connType);
			spin.setEnabled(true);
			parent.findViewById(R.id.btn_lock).setEnabled(true);
		}
	}
}
