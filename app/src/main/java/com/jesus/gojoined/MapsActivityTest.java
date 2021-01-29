package com.jesus.gojoined;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityTest extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_test);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng torrecardenas = new LatLng(36.862558, -2.441355);

        Marker newmarker=mMap.addMarker(new MarkerOptions().position(torrecardenas));
        newmarker.setTitle("Torrecardenas");
        newmarker.setDraggable(true);

        //mMap.addMarker(new MarkerOptions().position(torrecardenas).title("Marker in Sydney").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(torrecardenas));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng posicion=marker.getPosition();
                Toast.makeText(MapsActivityTest.this, posicion.longitude + ", " + posicion.latitude, Toast.LENGTH_SHORT).show();// display toast
                return true;  //false -> normal action;
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener()
        {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng posicion=marker.getPosition();
                Toast.makeText(MapsActivityTest.this, posicion.longitude + ", " + posicion.latitude, Toast.LENGTH_SHORT).show();// display toast
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
        {
            @Override
            public void onMapLongClick(LatLng mMapLatLng) {
                // TODO Auto-generated method stub

                //create new marker when user long clicks
                mMap.addMarker(new MarkerOptions()
                        .position(mMapLatLng)
                        .draggable(true));
            }
        });

    }
}
