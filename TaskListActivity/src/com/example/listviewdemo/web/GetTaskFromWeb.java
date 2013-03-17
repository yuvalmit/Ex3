package com.example.listviewdemo.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.listviewdemo.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;

public class GetTaskFromWeb extends AsyncTask<URL, Integer, JSONObject>
{
	
	
	protected JSONObject doInBackground(URL... urls)
	{
		JSONObject result = null;
		try
		{
			result = getFromWeb(urls[0]);
			
		} 
		catch (IOException e)
		{
			Log.d("juv","IOException");
			
		} 
		catch (URISyntaxException e)
		{	
			Log.d("juv","URISyntaxException");
		} 
		catch (JSONException e)
		{
			Log.d("juv","JSONException");
		}
		
		return result;
	}

	
	private JSONObject getFromWeb(URL url) throws IOException, URISyntaxException, JSONException 
	{
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		JSONObject response = null;
		response = executeHttpGet(urlConnection);
		return response;
	}

	public JSONObject executeHttpGet(HttpURLConnection URL) throws IOException, JSONException 
	{
		InputStream in = new BufferedInputStream(URL.getInputStream());
		InputStreamReader inReader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(inReader);
		StringBuilder responseBuilder = new StringBuilder();
		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine())
		{
			responseBuilder.append(line);
		}
		
		JSONObject response = new JSONObject(responseBuilder.toString());
		return response;
	    
	}
		
}
