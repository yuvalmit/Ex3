package com.example.listviewdemo;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TaskItem implements Comparable<TaskItem>
{
	private String taskName;
	private String taskDescription;
	private Date taskCreateDate;
	private Calendar taskEndDate;

	public Calendar getTaskEndDate()
	{
		return taskEndDate;
	}
	public void setTaskEndDate()
	{
		this.taskEndDate = new GregorianCalendar();
	}
	public void setTaskEndDate(Calendar Calendar)
	{
		
		this.taskEndDate = Calendar;
	}
	public TaskItem(String name, String dis)
	{	
		setTaskName(name);
		setTaskDescription(dis);
		setTaskCreateDate();
		setTaskEndDate();
		
	}
	public TaskItem(TaskItem obj)
	{	
		setTaskName(obj.getTaskName());
		setTaskDescription(obj.getTaskDescription());
		setTaskCreateDate();
		setTaskEndDate(obj.getTaskEndDate());
		
	}

	public TaskItem() {}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getTaskDescription()
	{
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription)
	{
		this.taskDescription = taskDescription;
	}
	public Date getTaskCreateDate() 
	{
		return taskCreateDate;
	}
	
	public void setTaskCreateDate()
	{
		
		this.taskCreateDate = new Date(System.currentTimeMillis());
		
	}
	public int compareTo(TaskItem obj) 
	{
		if (getTaskCreateDate() == null || obj.getTaskCreateDate() == null)
		      return 0;
		
		return getTaskCreateDate().compareTo(obj.getTaskCreateDate());
	}
}