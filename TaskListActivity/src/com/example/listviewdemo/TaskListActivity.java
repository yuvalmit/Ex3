package com.example.listviewdemo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

public class TaskListActivity extends Activity
{
	public static Context context;
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
				Intent intent = new Intent(v.getContext(),
						AddTaskActivity.class);
				startActivity(intent);

			}

		});
		
		lv1.setVisibility(View.GONE);
		Handler handler=new Handler();
		Runnable r=new Runnable()
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
	
	
            
	
}
