package com.example.john.locationgetter;
        import android.app.Activity;
        import android.content.Context;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements LocationListener {

    TextView lblLocation;
    Button btnShowLocation;
    //  private LocationManager locationManager;
    //  Location currentLocation;
    private static final String TAG5 = "Test Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblLocation = (TextView) findViewById(R.id.lblLocation);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);


        // Show location button click listener
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayLocation();
            }
        });
    }

    private void displayLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Log.i(TAG5,"Inside displayLocation1");
        if (locationManager != null) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, this);

            Log.i(TAG5, "Inside displayLocation2");
            Location  currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Log.i(TAG5,"Inside displayLocation3");
            if (currentLocation != null) {
                double latitude = currentLocation.getLatitude();
                double longitude = currentLocation.getLongitude();

                lblLocation.setText(latitude + ", " + longitude);

            }
        }

        else {

            lblLocation
                    .setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }

    }


    @Override
    public void onLocationChanged(Location location) {

        String str = "Latitude: "+location.getLatitude()+ "\n" +
                "Longitude: "+location.getLongitude();

        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
