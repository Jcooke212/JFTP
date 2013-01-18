package org.jcooke212.jftp;

import java.util.ArrayList;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FSListFrag extends ListFragment 
{
    /**
     * The fragment argument representing the section number for this fragment.
     */
    
    public static final String ARG_DISPLAY_TYPE = "display_type";
    public ArrayList<String> list = new ArrayList<String>();
    public ArrayAdapter<String> adapter;
    
    public FSListFrag()
    {	
	list.add("This is");
	list.add("the unchanged");
	list.add("list");
    }

    public void onResume()
    {
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
	FSHandler.LocalSystem.traverseFS(list, touched.getText().toString(), v.getContext());
	adapter.notifyDataSetChanged();
    }
}