package org.jcooke212.jftp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FileSystem extends ListFragment 
{
    /**
     * The fragment argument representing the section number for this fragment.
     */
    
    public static final String ARG_DISPLAY_TYPE = "display_type";
    
    public FileSystem(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
	ListView listView = (ListView) getActivity().findViewById(R.id.list);
	return listView;
    }
}