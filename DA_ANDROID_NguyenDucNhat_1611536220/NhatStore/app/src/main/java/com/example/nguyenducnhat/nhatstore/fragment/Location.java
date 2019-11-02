package com.example.nguyenducnhat.nhatstore.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenducnhat.nhatstore.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.nguyenducnhat.nhatstore.R.*;

public class Location extends Fragment {
    private GoogleMap mMap;
    MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(layout.fragment_location, container, false);
        mMapView = (MapView) v.findViewById(id.mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in shop and move the camera
                LatLng HONGPHUCSPORTSHOP = new LatLng(10.850683, 106.762088);
                mMap.addMarker(new MarkerOptions().position(HONGPHUCSPORTSHOP).title("Hồng Phúc shop").snippet("Shop").icon(BitmapDescriptorFactory.defaultMarker()));
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(HONGPHUCSPORTSHOP).zoom(90).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });
        return v;
    }

}
