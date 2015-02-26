package com.gleung.mapp;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class SetAlarmActivity extends Activity{
	
	public static final String STOP_POS="com.gleung.mapp.destination";
	private LatLng pos;
	private TextView coordinateBox;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		String stop_name=intent.getStringExtra(MainActivity.STOP_NAME);
		String stop_routes=intent.getStringExtra(MainActivity.STOP_TITLE);
		pos=(LatLng)intent.getParcelableExtra(MainActivity.STOP_POS);

		setContentView(R.layout.activity_set_alarm);
		TextView heading=(TextView)findViewById(R.id.bus_stop_name);
		heading.setTextSize(30);
		heading.setText(stop_name);
		
		TextView route=(TextView)findViewById(R.id.bus_routes);
		route.setTextSize(30);
		route.setText(stop_routes);
		
		TextView coordinates=(TextView)findViewById(R.id.bus_coordinates);
		coordinates.setTextSize(30);
		coordinates.setText(pos.latitude+"X"+pos.longitude);
		
		TextView coord=(TextView)findViewById(R.id.gps_coordinates);
		coord.setTextSize(20);

		coordinateBox=(TextView)findViewById(R.id.gps_coordinates_val);
		coordinateBox.setTextSize(20);
		coordinateBox.setText("establishing...");

		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		    	coordinateBox.setText(location.getLatitude()+" X "+location.getLongitude());
		    	checkForDestination(location);
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		    public void checkForDestination(Location location){
		    	
		    }
		  };

		// Register the listener with the Location Manager to receive location updates
		  locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}
	
	public void alarmWatch(View view){
		//Let's do a geofence.
//		coordinateBox.setText("We ballin");
		
		Intent intent=new Intent(this, WaitAlarmActivity.class);
		intent.putExtra(STOP_POS,pos);
		startActivity(intent);
	}
}
