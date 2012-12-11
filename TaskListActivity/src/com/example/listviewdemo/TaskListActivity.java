package com.example.listviewdemo;

import com.example.listviewdemo.progressbar.myProgressBar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.view.*;
import android.view.View.*;
import android.widget.ListView;
import android.view.View;

public class TaskListActivity extends Activity
{
	myProgressBar myBar = new myProgressBar();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final taskListAdapter adapter = new taskListAdapter(this, TaskArry.getInstance(this));
		
		
		final ListView lv1 = (ListView) findViewById(R.id.listView1);
		lv1.setAdapter(adapter);	
        
		Button addTask = (Button) findViewById(R.id.addTaskButtonMain);
		addTask.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(),
						AddTaskActivity.class);
				startActivity(intent);

			}

		});
		
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
	
	
            
	
}
