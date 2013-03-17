package com.example.listviewdemo;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class ReminderBroadCastReceiver extends BroadcastReceiver
{

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.d("juv","gotnotification");
		Intent myIntent = new Intent(context, TaskListActivity.class);
		PendingIntent pendingintent = PendingIntent.getActivity(context, 0, myIntent, 0);
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification noti = new Notification.Builder(TaskListActivity.context)
		.setContentTitle(intent.getStringExtra("title"))
        .setContentText("ME Remaind YOU :)").setSmallIcon(R.drawable.ic_launcher)
        .setContentIntent(pendingintent).build();
 
		
		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(6, noti); 
		Log.d("juv","gotnotification");
	}

}
