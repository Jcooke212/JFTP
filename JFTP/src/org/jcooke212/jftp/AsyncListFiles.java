package org.jcooke212.jftp;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

import android.os.AsyncTask;

public class AsyncListFiles extends AsyncTask<String, Void, String[]>
{
	@Override
	protected String[] doInBackground(String... params) 
	{
    	FTPClient ftp = new FTPClient();
    	try 
    	{
    		//Hard coded for testing purposes
    		ftp.connect("SERVER ADDRESS");			
			ftp.login("USERNAME", "PASSWORD");
			String[] files = ftp.listNames();
			return files;
		} 
    	catch (IOException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

