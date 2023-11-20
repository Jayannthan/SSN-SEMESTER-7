package com.example.exercise6b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (ContextCompat.checkSelfPermission(MainActivity.this,
				Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(MainActivity.this, new String[] {
					Manifest.permission.ACCESS_FINE_LOCATION
			}, 100);
		}
		;

		Button find = (Button) findViewById(R.id.find);
		find.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				findLocation();
			}
		});
	}

	@SuppressLint("MissingPermission")
	private void findLocation() {
		try {
			LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, MainActivity.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLocationChanged(@NonNull Location location) {
		TextView latitude = (TextView) findViewById(R.id.latitude);
		TextView longitude = (TextView) findViewById(R.id.longitude);
		try {
			Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

			TextView current_address = (TextView) findViewById(R.id.address);
			String address_name = current_address.getText().toString();

			List<Address> addresses = geocoder.getFromLocationName(address_name, 1); // Here 1 represent max location
																						// result to returned, by
																						// documents it recommended 1 to
																						// 5

			Address address_details = addresses.get(0);

			Double get_latitude = (double) Math.round(address_details.getLatitude() * 100) / 100;
			Double get_longitude = (double) Math.round(address_details.getLongitude() * 100) / 100;

			latitude.setText(String.valueOf(get_latitude));
			longitude.setText(String.valueOf(get_longitude));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
