package org.jcooke212.jftp;

import java.util.ArrayList;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class FileSystem extends ListFragment 
{
    /**
     * The fragment argument representing the section number for this fragment.
     */
    
    public static final String ARG_DISPLAY_TYPE = "display_type";
    public ArrayList<String> list = new ArrayList<String>();
    
    public FileSystem()
    {	
	list.add("Test again");
	list.add("This better work!!");
	list.add("Im sick of this!!!!");
    }

    public void onResume()
    {
	super.onResume();
	ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
	setListAdapter(adapter);
    }
}