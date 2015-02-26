package com.gleung.mapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class GeoFenceIntentService extends IntentService {
	public static final String TRANSITION_INTENT_SERVICE = "ReceiveTransitionsIntentService";
	
	public GeoFenceIntentService() {
	    super(TRANSITION_INTENT_SERVICE);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		generateNotification();
	}
	
	private void generateNotification() {
	    long when = System.currentTimeMillis();
	    Intent notifyIntent = new Intent(this, AlarmActivity.class);
	    notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	    
	    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	
	    NotificationCompat.Builder builder =
	            new NotificationCompat.Builder(this)
	                    .setContentIntent(pendingIntent)
	                    .setAutoCancel(true)
	                    .setDefaults(Notification.DEFAULT_SOUND)
	                    .setWhen(when);
	
	    NotificationManager notificationManager =
	            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    notificationManager.notify((int) when, builder.build());
	}
}