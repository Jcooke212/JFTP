package org.jcooke212.jftp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.os.Environment;

public class FSHandler
{
    public static class LocalSystem
    {
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
	}
	
	public static void traverseFS(ArrayList<String> list, String target)
	{

	}
    }
    public static class RemoteSystem
    {
	
    }
}
