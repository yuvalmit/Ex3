package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import android.content.Context;

import com.example.listviewdemo.DB.DatabaseHandler;


public class TaskArry
{
	private static TaskArry instance ;
	private ArrayList<TaskItem> theList = new ArrayList<TaskItem>();
	private static DatabaseHandler db; 

	private TaskArry()
	{
		
		theList = db.getAllTask();
		TaskItem item = new TaskItem("תזכורות","");
		item.setIsLable(true);
		item.setTaskCreateDate(System.currentTimeMillis()-86400000*365);
		theList.add(item);
		
	}

	public static TaskArry getInstance(Context context)
	{
		if(instance == null)
		{
			db = DatabaseHandler.getInstance(context);
			instance = new TaskArry();
		}
		return instance;
	}
	

	
	public void addItem(String name, String dis)
	{
		theList.add(new TaskItem(name,dis));
		db.addTask(new TaskItem(name,dis));
	}
	
	public void addItem(TaskItem obj)
	{
		theList.add(new TaskItem(obj));
		db.addTask(new TaskItem(obj));
	}
	public void addItemDB(String name, String dis)
	{
		
		db.addTask(new TaskItem(name,dis));
	}
	
	public TaskItem getItem(int index)
	{
		return theList.get(index);
		
	}
	
	public void delItem(int index)
	{
		
		 db.deleteTask(getItem(index));
		 theList.remove(index);
	}
	
	public int getSize()
	{
		return theList.size();
	}
	
	public void sortByDate(final int dir)//2-new to old, 1-old to new, default old to new
	{ 
		Collections.sort(theList, new Comparator<TaskItem>() 
			{
			  public int compare(TaskItem o1, TaskItem o2) 
			  {
				  switch(dir)
				  {
				  	case 1:
				  		return o2.getTaskCreateDate().compareTo(o1.getTaskCreateDate());
				  	case 2:
				  		return o1.getTaskCreateDate().compareTo(o2.getTaskCreateDate());
				  	default :
				  		return o2.getTaskCreateDate().compareTo(o1.getTaskCreateDate());
				  }
			  }
			});
		
	}
	
}