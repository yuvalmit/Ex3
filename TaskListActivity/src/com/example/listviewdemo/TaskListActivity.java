package com.example.listviewdemo;

import java.util.Iterator;
import java.util.List;

import com.example.listviewdemo.service.GetTaskFromWebService;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

public class TaskListActivity extends Activity
{
	private static long		MILLIS_IN_DAY	= 86400000;
	public static Context	context;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TaskListActivity.context = getApplicationContext();
		setContentView(R.layout.main);
		final taskListAdapter adapter = new taskListAdapter(this, TaskArry.getInstance(this));

		final ProgressBar prog = (ProgressBar) findViewById(R.id.progressBar1);
		final ListView lv1 = (ListView) findViewById(R.id.listView1);

		lv1.setAdapter(adapter);

		Button addTask = (Button) findViewById(R.id.addTaskButtonMain);
		addTask.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), AddTaskActivity.class);
				startActivity(intent);

			}

		});
		final ToggleButton webTask = (ToggleButton) findViewById(R.id.webbutton);
		webTask.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				if (webTask.isChecked())
				{
					Log.d("juv", "web task checked");
					Intent intent = new Intent(context, GetTaskFromWebService.class);
					PendingIntent pendingIntent = PendingIntent.getService(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
					AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
					alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (System.currentTimeMillis() % MILLIS_IN_DAY), MILLIS_IN_DAY, pendingIntent);
					Log.d("juv", "web task alarm sent");
				} else
				{
					Log.d("juv", "web task unchecked");
					Intent intent = new Intent(context, GetTaskFromWebService.class);
					PendingIntent pendingIntent = PendingIntent.getService(context, 2, intent, 0);
					AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
					alarmManager.cancel(pendingIntent);
				}

			}

		});

		if (isServiceRunning())
		{
			webTask.setChecked(true);
		}

		lv1.setVisibility(View.GONE);
		Handler handler = new Handler();
		Runnable r = new Runnable()
		{
			public void run()
			{
				lv1.setVisibility(View.VISIBLE);
				prog.setVisibility(View.GONE);

			}
		};
		handler.postDelayed(r, 1500);

	}

	public void onResume()
	{
		super.onResume();
		final ListView lv1 = (ListView) findViewById(R.id.listView1);
		lv1.setAdapter(new taskListAdapter(this, TaskArry.getInstance(this)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_task_list, menu);
		return true;
	}

	private boolean isServiceRunning()
	{
		Intent checkIntent = new Intent(context,GetTaskFromWebService.class);
		return (PendingIntent.getService(getBaseContext(), 2, checkIntent, PendingIntent.FLAG_NO_CREATE) != null);
	}

	public boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting())
		{
			return true;
		}
		return false;
	}

}
