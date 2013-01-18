package org.jcooke212.jftp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class FSHandler
{
    public static class LocalSystem
    {
	private static String currentDir;
	public static void getFileSystem(ArrayList<String> list)
	{
	    list.clear();
	    File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
	    File[] files = root.listFiles();
	    for(File x: files)
	    {
		list.add(x.getName());
	    }
	    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
	    currentDir = root.getAbsolutePath();
	}
	
	public static void traverseFS(ArrayList<String> list, String target, Context appContext)
	{
	    
	    currentDir += "/" + target;
	    File dir = new File(currentDir);
	    if(dir.isDirectory())
	    {
		list.clear();
		File[] files = dir.listFiles();
		for(File x: files)
		{
		    	list.add(x.getName());
		}
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		currentDir = dir.getAbsolutePath();
	    }
	    else
	    {
		Toast.makeText(appContext, "Not a directory", Toast.LENGTH_LONG).show();
	    }
	}
    }
    public static class RemoteSystem
    {
	
    }
}
