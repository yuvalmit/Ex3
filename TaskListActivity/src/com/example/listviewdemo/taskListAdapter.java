package com.example.listviewdemo;



import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;



public class taskListAdapter extends BaseAdapter
{
	TaskArry myList;
	private LayoutInflater l_Inflater;

	static class ViewHolder
	{
		TextView txt_taskName;
		TextView txt_taskDescription;
		TextView txt_taskDateCreate;
		TextView txt_taskDateEnd;
		Button delTask;
	}

	public taskListAdapter(Context context, TaskArry task_details)
	{

		myList = task_details;
		myList.sortByDate(2);
		l_Inflater = LayoutInflater.from(context);

	}

	public void addItem(TaskItem obj)
	{
		myList.addItem(obj);
	}

	public int getCount()
	{
		return myList.getSize();
	}

	public TaskItem getItem(int index)
	{
		return myList.getItem(index);
	}

	public long getItemId(int index)
	{
		return index;
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		final ViewHolder holder;

		if (convertView == null)
		{
			convertView = l_Inflater.inflate(R.layout.activity_task_list, null);
			holder = new ViewHolder();
			holder.txt_taskName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_taskDescription = (TextView) convertView.findViewById(R.id.dis);
			//holder.txt_taskDateCreate = (TextView) convertView.findViewById(R.id.createdate);
			holder.txt_taskDateEnd = (TextView) convertView.findViewById(R.id.enddate);
			holder.delTask = (Button) convertView.findViewById(R.id.delTask);
			convertView.setTag(holder);
			convertView.setOnTouchListener(new OnTouchListener()
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
							holder.delTask.setVisibility(View.INVISIBLE);
							holder.txt_taskName.setPadding(0, 0, 0, 0);
							holder.txt_taskDescription.setPadding(0, 0, 0, 0);
							//showToastNotification("invisible "+event.getX()+","+oldX);
							return false;
						} else if (event.getX() - oldX < DELTA/* && event.getX() < oldX*/)
						{
							holder.delTask.setVisibility(View.VISIBLE);
							holder.txt_taskName.setPadding(0, 0, 80, 0);
							holder.txt_taskDescription.setPadding(0, 0, 80, 0);
							//showToastNotification("visible "+event.getX()+","+oldX);
							
							return false;
						}
						
						
						break;
					}
					return true;
				}
			});
			
		} 
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		holder.txt_taskName.setText(myList.getItem(position).getTaskName());
		holder.txt_taskDescription.setText(myList.getItem(position).getTaskDescription());
		//holder.txt_taskDateCreate.setText(sdf.format(myList.getItem(position).getTaskCreateDate()));
		holder.txt_taskDateEnd.setText("תאריך אחרון לביצוע: "+sdf.format(myList.getItem(position).getTaskEndDate().getTime()));
		holder.delTask.getBackground().setColorFilter(new LightingColorFilter(0xFF0000, 0x000000))   ;
		holder.delTask.setVisibility(View.INVISIBLE);
		holder.delTask.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				myList.delItem(position);
				notifyDataSetChanged();

			}
		});

		return convertView;

	}
	
	
	

}
