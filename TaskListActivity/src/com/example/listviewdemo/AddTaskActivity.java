package com.example.listviewdemo;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends Activity
{
	EditText addText;
	EditText addDis;
	DatePicker endDate;
	Button btnChangeDate;
	taskListAdapter adapter;
	
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
		v.setOnTouchListener(new OnTouchListener()
		{
			
			public boolean onTouch(View v, MotionEvent event)
			{
				float oldX = event.getHistoricalX(0);
				final int DELTA = 30;
				
				switch (event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					oldX = event.getX();
					break;
				
				case MotionEvent.ACTION_UP:
					if (event.getX() - oldX > DELTA/* && event.getX() > oldX*/)
					{
						showToastNotification("Add more task!!");
						
					} else if (event.getX() - oldX < DELTA/* && event.getX() < oldX*/)
					{
						onBackPressed();
						showToastNotification("left");
					}
					
					
					break;
				}
				
				return true;
			}

			
		 });
		
		Button addBT = (Button) findViewById(R.id.buttonAdd);
		addText = (EditText) findViewById(R.id.editTextTaskName);
		addDis = (EditText) findViewById(R.id.editTextTaskDisc);
		endDate = (DatePicker) findViewById(R.id.datePicker1);
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
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

			}
		});
	
		endDate.setVisibility(View.INVISIBLE);
		
	}
	
	private void showToastNotification(String string)
	{
		Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
		
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

	

		
	
}


