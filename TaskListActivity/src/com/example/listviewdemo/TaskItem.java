package com.example.listviewdemo;




public class TaskItem implements Comparable<TaskItem>
{
	

	private int id;
	private String taskName;
	private String taskDescription;
	private Long taskCreateDate = (long) 0;
	private Long taskEndDate = (long) 0 ;
	private Boolean isLable = false;
	private Boolean isAlarm = false;

	
	
	public TaskItem( String name, String dis)
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
	public Long getTaskCreateDate() 
	{
		return taskCreateDate;
	}
	public void setTaskCreateDate()
	{
		
		this.taskCreateDate = System.currentTimeMillis();
		
	}
	public void setTaskCreateDate(long timeInMilli)
	{
		
		this.taskCreateDate = timeInMilli;
		
	}
	public Long getTaskEndDate()
	{
		return taskEndDate;
	}
	public void setTaskEndDate()
	{
		this.taskEndDate =  System.currentTimeMillis();
	}
	public void setTaskEndDate(long timeInMilli)
	{
		
		this.taskEndDate = timeInMilli;
	}
	public int compareTo(TaskItem obj) 
	{
		if (getTaskCreateDate() == 0 || obj.getTaskCreateDate() == 0)
		      return 0;
		
		return getTaskCreateDate().compareTo(obj.getTaskCreateDate());
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Boolean getIsLable()
	{
		return isLable;
	}
	public void setIsLable(Boolean isLable)
	{
		this.isLable = isLable;
	}
	public Boolean getIsAlarm()
	{
		return isAlarm;
	}
	public void setIsAlarm(Boolean isAlarm)
	{
		this.isAlarm = isAlarm;
	}
}