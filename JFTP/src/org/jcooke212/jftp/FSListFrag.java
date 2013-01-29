package org.jcooke212.jftp;

import java.io.IOException;
import java.util.ArrayList;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FSListFrag extends ListFragment 
{
    /**
     * The fragment argument representing the section number for this fragment.
     */
    
    public static final String ARG_DISPLAY_TYPE = "display_type";
    public ArrayList<String> list = new ArrayList<String>();
    public ArrayAdapter<String> adapter;
    
    public void onResume()
    {
    	this.setEmptyText("Use the menu to add a server");
		super.onResume();
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
    
    public void onListItemClick(ListView l, View v, int position, long id)
    {
    	TextView touched = (TextView) v;
		try 
		{
			FSHandler.LocalSystem.traverseFS(list, touched.getText().toString(), v.getContext());
		} 
		catch (IOException e) 
		{
			if(e.getMessage() != null)
			{
				Toast.makeText(getActivity(), R.string.res_error, Toast.LENGTH_LONG).show();
			}
		}
		adapter.notifyDataSetChanged();
    }
}