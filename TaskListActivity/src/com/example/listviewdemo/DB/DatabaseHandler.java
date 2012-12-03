package com.example.listviewdemo.DB;

import com.example.listviewdemo.TaskItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{

	
	// Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "taskManager";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "tasks";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TASKNAME = "name";
    private static final String KEY_TASKDIS = "discription";
    private static final String KEY_ENDTASK = "end date";
	
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String CREATE_CONTACTS_TABLE = 
		"CREATE TABLE " + TABLE_CONTACTS + "("
        										+ KEY_ID + " INTEGER PRIMARY KEY," 
        										+ KEY_TASKNAME + " TEXT,"
        										+ KEY_TASKDIS + " TEXT" 
        										+ KEY_ENDTASK + " TEXT" +")";
		db.execSQL(CREATE_CONTACTS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
		
	}
	
	public void addContact(TaskItem item) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_TASKNAME, item.getTaskName()); // task Name
	    values.put(KEY_TASKDIS, item.getTaskDescription()); // task discription
	    values.put(KEY_TASKDIS, item.); // task end date
	 
	    // Inserting Row
	    db.insert(TABLE_CONTACTS, null, values);
	    db.close(); // Closing database connection
	}

}
