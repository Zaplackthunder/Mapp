package com.gleung.mapp;

import java.util.List;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WaitAlarmActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener{

	private TextView text_view;
	private LatLng pos;
	private final float radius=10.0f;
	private final int loiteringDelayMs=10, notificationResponsivenessMs=3000,transitionTypes=Geofence.GEOFENCE_TRANSITION_ENTER;
	private final long durationMillis=Geofence.NEVER_EXPIRE;
	private final String requestId="ID";
	
	private GoogleApiClient google;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		pos=intent.getParcelableExtra(SetAlarmActivity.STOP_POS);
		
		
		text_view=new TextView(this);
		text_view.setTextSize(15);
		text_view.setText("Waiting on Google.");
		setContentView(text_view);
		
		google=  new GoogleApiClient.Builder(this)
	     .addApi(LocationServices.API)
	     .addConnectionCallbacks(this)
	     .addOnConnectionFailedListener(this)
	     .build();
		google.connect();
		
	}

	public void onConnectionFailed(ConnectionResult arg0) {
		text_view.setText("Couldn't connect to Google.");
		//fuck
	}

	public void onConnected(Bundle arg0) {
		text_view.setText("Couldn't connect to Google. Please check connection...");
		
		Geofence build;
		Geofence.Builder builder=new Geofence.Builder();
		builder.setCircularRegion(pos.latitude, pos.longitude, radius);
		builder.setExpirationDuration(durationMillis);
		builder.setLoiteringDelay(loiteringDelayMs);
		builder.setNotificationResponsiveness(notificationResponsivenessMs);
		builder.setRequestId(requestId);
		builder.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
		build=builder.build();
		
		Intent intent=new Intent(this, AlarmActivity.class);
		PendingIntent pendIntent=PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		GeofencingRequest.Builder rbuild=new GeofencingRequest.Builder();
		rbuild.addGeofence(build);
		rbuild.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
		
		GeofencingRequest gfencereq=rbuild.build();
		
		LocationServices.GeofencingApi.addGeofences(google,gfencereq,pendIntent);
	}

	public void onConnectionSuspended(int arg0) {
		text_view.setText("Couldn't connect to Google. Suspended.");
		//double fuck
		
	}
//
//	@Override
//	public PendingResult<Status> addGeofences(GoogleApiClient arg0,
//			List<Geofence> arg1, PendingIntent arg2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PendingResult<Status> addGeofences(GoogleApiClient arg0,
//			GeofencingRequest arg1, PendingIntent arg2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PendingResult<Status> removeGeofences(GoogleApiClient arg0,
//			PendingIntent arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PendingResult<Status> removeGeofences(GoogleApiClient arg0,
//			List<String> arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
