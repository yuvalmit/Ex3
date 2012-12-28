package com.example.listviewdemo;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddTaskActivity extends Activity
{
	EditText addText;
	EditText addDis;
	DatePicker endDate;
	Button btnChangeDate;
	taskListAdapter adapter;
	CheckBox check;
	
	private int year;
	private int month;
	private int day;
	
	static final int DATE_DIALOG_ID = 999;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		View v = getLayoutInflater().inflate(R.layout.activity_add_task,null);
		setContentView(v);		
		Button addBT = (Button) findViewById(R.id.buttonAdd);
		addText = (EditText) findViewById(R.id.editTextTaskName);
		addDis = (EditText) findViewById(R.id.editTextTaskDisc);
		endDate = (DatePicker) findViewById(R.id.datePicker1);
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
		check = (CheckBox) findViewById(R.id.checkBox1);
		btnChangeDate.setOnClickListener(new OnClickListener() 
		{

			@SuppressWarnings("deprecation")
			public void onClick(View v)
			{
				showDialog(DATE_DIALOG_ID);
				
			}
			 
		
		});
		
		adapter = new taskListAdapter(this, TaskArry.getInstance(this));
		setCurrentDateOnView();
		addBT.setOnClickListener(new OnClickListener()
		{


			public void onClick(View v)
			{
				TaskItem obj = new TaskItem();
				obj.setTaskName(addText.getText().toString());
				obj.setTaskDescription(addDis.getText().toString());
					long endTime;
					final Calendar cal = Calendar.getInstance();
					cal.set(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), 9, 0, 0);
					endTime = cal.getTimeInMillis();
				obj.setTaskEndDate(endTime);
				adapter.addItem(obj);
				// return and recreate task list activity
				/*
				 * Intent intent = new
				 * Intent(v.getContext(),TaskListActivity.class);
				 * startActivity(intent);
				 */
				// clear data and use onresume when back button is pressed
				addText.setText("");
				addDis.setText("");
				if (check.isChecked())
				{
					alarm();
				}
				check.setChecked(false);

			}
		});
		
		endDate.setVisibility(View.INVISIBLE);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_task_list, menu);
		return true;
	}
	
	public void setCurrentDateOnView() 
	{
		 
		
		long now;
		final Calendar cal = Calendar.getInstance();
		now = cal.getTimeInMillis()+(86400000*7);
		cal.setTimeInMillis(now);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		// set current date into date picker
		endDate.init(year, month, day, null);
 
	}
	
	protected Dialog onCreateDialog(int id) 
	{
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, year, month,day);
		}
		return null;
	}
 
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() 
	{
		
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) 
		{
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			// set selected date into date picker also
			endDate.init(year, month, day, null);
 
		}
	};
	
	private void alarm()
	{
		Log.d("juv","checked1");
		Intent intent = new Intent("com.example.listview.BROADCAST");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(TaskListActivity.context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()	+ 2000, pendingIntent);
		Log.d("juv","checked2");
	}


	

		
	
}


