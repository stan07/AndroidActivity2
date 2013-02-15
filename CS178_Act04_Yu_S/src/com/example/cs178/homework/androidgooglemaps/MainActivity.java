package com.example.cs178.homework.androidgooglemaps;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private GoogleMap gMap;
	private int locator = 0;
	private String alertMsg;
	
	static final LatLng TALAMBAN = new LatLng(10.35410, 123.91145);
	static final LatLng MAIN = new LatLng(10.30046, 123.88822);
	static final LatLng SECRETPLACE = new LatLng(22.28021, 114.18411);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		if(gMap != null)
		{
			Marker tc = gMap.addMarker(new MarkerOptions().position(TALAMBAN).title("USC-TC").snippet("University of San Carlos - Talamban Campus"));
			Marker main = gMap.addMarker(new MarkerOptions().position(MAIN).title("USC-MAIN").snippet("University of San Carlos - Main Campus"));
			Marker secret = gMap.addMarker(new MarkerOptions().position(SECRETPLACE).title("CAUSEWAY BAY").snippet("A place in Hong Kong"));
			
			gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TALAMBAN, 19), 2000, null);
			
			gMap.setOnMarkerClickListener(new OnMarkerClickListener() {
				
				@Override
				public boolean onMarkerClick(Marker marker) {
					DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch(which)
							{
								case DialogInterface.BUTTON_POSITIVE: 
									switch(locator)
									{
										case 0: gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TALAMBAN, 19), 2000, null);
												alertMsg = "Do you want to go to USC-MAIN?";
												break;
												
										case 1: gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TALAMBAN, 19), 2000, null);
												alertMsg = "Do you want to go to the secret place?";
												break;
												
										case 2: gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TALAMBAN, 19), 2000, null);
												alertMsg = "Do you want to go to USC-TALAMBAN?";
												break;
									}
									locator = (locator + 1) % 3;
									break;
									
								default: break;
							}
							
						}
					};
					
					AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
					alert.setMessage(alertMsg).setPositiveButton("Yes", dialog).setNegativeButton("No", dialog).show();
					
					return false;
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
