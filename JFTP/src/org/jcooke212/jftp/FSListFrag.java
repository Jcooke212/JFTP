package org.jcooke212.jftp;

import java.io.IOException;
import java.util.ArrayList;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/*****************************************************************************************************
 * The fragment argument representing the section number for this fragment. 
 *****************************************************************************************************/
public class FSListFrag extends ListFragment 
{
	
    public static final String ARG_DISPLAY_TYPE = "display_type";
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    /*************************************************************************************************
     *  Set up the ListView of this class depending on which UI fragment is being created
     *************************************************************************************************/
    @Override
    public void onResume()
    {
    	super.onResume();
    	this.setEmptyText("Use the menu to add a server");
		String type = getArguments().getString(ARG_DISPLAY_TYPE);
		if(type.equals("display_local"))
		{
		    FSHandler.LocalSystem.getFileSystem(list);
		}
		else if(type.equals("display_remote"))
		{
		}
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);	
    }
 
    /*************************************************************************************************
     * When a file is click alert user that it isn't a directory. When a folder is clicked traverse to
     * the new folder and notify the adapter of data change. 
     *************************************************************************************************/
    public void onListItemClick(ListView l, View v, int position, long id)
    {
    	TextView touched = (TextView) v;
		try 
		{
			FSHandler.LocalSystem.traverseFS(list, touched.getText().toString(), v.getContext());
			adapter.notifyDataSetChanged();
		} 
		catch (IOException e) 
		{
			if(e.getMessage() != null)
			{
				Toast.makeText(getActivity(), R.string.res_error, Toast.LENGTH_LONG).show();
			}
		}
    }
}