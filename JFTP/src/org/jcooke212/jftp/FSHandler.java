package org.jcooke212.jftp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.net.ftp.*;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

/*****************************************************************************************************
 * This class will handle changes to the @ArrayList for each @FSListFrag
 * @author James Cooke
 *****************************************************************************************************/
public class FSHandler
{
	
	/***************************************************************************************************************
	 * This class handles the user visible FileSystem on the device
	 * @author James
	 ***************************************************************************************************************/
    public static class LocalSystem
    {
    	private static String currentDir;
    	
    	/*********************************************************************************************
    	 * Get the root user storage directory and populate a list for a fragment
    	 * @param list The @ArrayList to be populated with the contents of the directory
    	 *********************************************************************************************/
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
	
    	/*********************************************************************************************
    	 * Method to move through the file system on the device.
    	 * @param list The list that will be populated with the contents of the selected directory.
    	 * @param target The name of the file that was clicked.
    	 * @param appContext @Context of the application. (this is used to make a @Toast) 
    	 * @throws IOException will be thrown if a canonical path can't be found.
    	 *********************************************************************************************/
		public static void traverseFS(ArrayList<String> list, String target, Context appContext) throws IOException
		{
	    
		    currentDir += "/" + target;
		    File dir = new File(currentDir);
		    File root = Environment.getExternalStorageDirectory();
		    if(dir.isDirectory())
		    {
		    	list.clear();
		    	if(!dir.getCanonicalPath().equals(root.getCanonicalPath()))
		    	{
		    		list.add("..");
		    	}
		    	File[] files = dir.listFiles();
		    	for(File x: files)
				{
				    list.add(x.getName());
				}
		    	Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
				currentDir = dir.getCanonicalPath();
			}
		    else
			{
		    	Toast.makeText(appContext, R.string.not_dir, Toast.LENGTH_LONG).show();
		    	currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
			}
		}
    }
    
    /*************************************************************************************************
     * This class handles the user visible FileSystem on the server
     * @author James Cooke
     *************************************************************************************************/
    public static class RemoteSystem
    {
    	public static void getFileSystem(ArrayList<String> list, Context appContext)
    	{
    		FTPClient ftp = new FTPClient();
    		try 
    		{
    			ftp.connect("server.jcooke212.org");
				if(ftp.login("james", "blah"))
				{
					Toast.makeText(appContext, "YAYYY!!!", Toast.LENGTH_LONG).show();
				}
			} 
    		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    	}
    }
}
