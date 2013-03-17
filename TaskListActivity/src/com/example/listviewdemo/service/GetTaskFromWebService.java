package com.example.listviewdemo.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.listviewdemo.TaskArry;
import com.example.listviewdemo.web.GetTaskFromWeb;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class GetTaskFromWebService extends IntentService
{
	public static Boolean	isAlive	= false;

	public GetTaskFromWebService()
	{
		super("GetTaskFromWebService");
		Log.d("juv", "Service start");
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		isAlive = true;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		isAlive = false;
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{

		try
		{

			URL url = new URL("http://mobile1-tasks-dispatcher.herokuapp.com/task/random");
			GetTaskFromWeb getWebTask = new GetTaskFromWeb();
			getWebTask.execute(url);
			JSONObject result = getWebTask.get();

			TaskArry list = TaskArry.getInstance(this);

			list.addItemDB((String) result.get("topic"), (String) result.get("description"));
			Log.d("juv", "added to DB");
			Log.d("juv", GetTaskFromWebService.isAlive.toString());
		}

		catch (MalformedURLException e)
		{
			Log.d("juv", "Service MalformedURLException");
		} catch (InterruptedException e)
		{
			Log.d("juv", "Service InterruptedException");
		} catch (ExecutionException e)
		{
			Log.d("juv", "Service ExecutionException");
		} catch (JSONException e)
		{
			Log.d("juv", "Service JSONException");
		}

	}

}
