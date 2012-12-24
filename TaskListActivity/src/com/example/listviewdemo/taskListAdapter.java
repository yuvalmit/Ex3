package com.example.listviewdemo;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;



public class taskListAdapter extends BaseAdapter
{
	TaskArry myList;
	private LayoutInflater l_Inflater;
	private Context adapterContext;

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
		adapterContext = context;
		myList = task_details;
		l_Inflater = LayoutInflater.from(adapterContext);

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
		switch(getItemViewType(position))
		{
		case 1:
			final ViewHolder holder;
			if (convertView == null)
			{
				convertView = l_Inflater.inflate(R.layout.activity_task_list, null);
				holder = new ViewHolder();
				holder.txt_taskName = (TextView) convertView.findViewById(R.id.name);
				holder.txt_taskDescription = (TextView) convertView.findViewById(R.id.dis);
				holder.txt_taskDateEnd = (TextView) convertView.findViewById(R.id.enddate);
				holder.delTask = (Button) convertView.findViewById(R.id.delTask);
				holder.delTask.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						int pos = (Integer) v.getTag();
						TaskArry.getInstance(adapterContext).delItem(pos);
						notifyDataSetChanged();
	
					}
				});
				convertView.setTag(holder);
				
			} 
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			holder.txt_taskName.setText(myList.getItem(position).getTaskName());
			holder.txt_taskDateEnd.setText("לביצוע עד: "+sdf.format(new Date( myList.getItem(position).getTaskEndDate())));
			holder.delTask.setTag(position);
			holder.delTask.setBackgroundResource(R.drawable.round1);
			//make color gradient
			convertView.setBackgroundColor(Color.argb(255, position*(255/getCount()/2) ,position*(255/getCount()/2) , 255));
			return convertView;
		case 0:
			final ViewHolder labelHolder;
			if (convertView == null)
			{
				convertView = l_Inflater.inflate(R.layout.activity_task_list_label, null);
				labelHolder = new ViewHolder();
				labelHolder.txt_taskName = (TextView) convertView.findViewById(R.id.textViewLabel);
				
				convertView.setTag(labelHolder);
				
			} 
			else
			{
				labelHolder = (ViewHolder) convertView.getTag();
			}
			
		
			labelHolder.txt_taskName.setText(myList.getItem(position).getTaskName());
			labelHolder.txt_taskName.setBackgroundColor(Color.argb(155, 0 , 0 , 255));
			
			
			
			return convertView;
		default:
			return null;
		}
		
		

	}
	
	public int getViewTypeCount() 
	{
	    return 2;
	}
	
	
	public int getItemViewType(int position) 
	{

	    //is a label
	    if (myList.getItem(position).getIsLable() == true) return 0;

	    //is a regular item
	    else return 1;
	}
	
	

}
