package com.example.exercise5;

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
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					5000, 5, MainActivity.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLocationChanged(@NonNull Location location) {
		TextView latitude = (TextView) findViewById(R.id.latitude);
		TextView longitude = (TextView) findViewById(R.id.longitude);
		String get_latitude = latitude.getText().toString();
		latitude.setText(get_latitude + location.getLatitude());
		String get_longitude = longitude.getText().toString();
		longitude.setText(get_longitude + location.getLongitude());
		try {
			Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			String address = addresses.get(0).getAddressLine(0);

			TextView current_address = (TextView) findViewById(R.id.address);
			current_address.setText(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
