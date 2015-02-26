package com.gleung.mapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends Activity implements OnMapReadyCallback, OnInfoWindowClickListener{

	public final static String STOP_NAME="com.gleung.mapp.BUS_STOP_NAME";
	public final static String STOP_TITLE="com.gleung.mapp.BUS_STOP_TITLE";
	public final static String STOP_POS="com.gleung.mapp.BUS_STOP_POSITION";
	
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mapFrag=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

	public void onMapReady(GoogleMap map) {
		map.setOnInfoWindowClickListener(this);
		map.addMarker(new MarkerOptions().position(new LatLng(43.73254,-79.34233)).title("25 South").snippet("Don Mills Rd At The Donway West (South)"));
		map.addMarker(new MarkerOptions().position(new LatLng(43.7373099,-79.3436)).title("25 South").snippet("Don Mills Rd At Lawrence Ave East"));
		map.addMarker(new MarkerOptions().position(new LatLng(43.73461,-79.34287)).title("25 South").snippet("Don Mills Rd At Clock Tower Rd"));
		map.addMarker(new MarkerOptions().position(new LatLng(43.7369399,-79.3439)).title("25 South").snippet("Lawrence Ave East At Don Mills Rd"));
		map.addMarker(new MarkerOptions().position(new LatLng(42.292325,-83.715294)).title("Ann Arbor").snippet("MHacks"));
	}

	public void onInfoWindowClick(Marker marker) {
		Intent intent=new Intent(this, SetAlarmActivity.class);
		String name=marker.getTitle();
		String stop=marker.getSnippet();
		LatLng pos=marker.getPosition();
		intent.putExtra(STOP_TITLE,name);
		intent.putExtra(STOP_NAME,stop);
		intent.putExtra(STOP_POS, pos);
		startActivity(intent);
	}
}
