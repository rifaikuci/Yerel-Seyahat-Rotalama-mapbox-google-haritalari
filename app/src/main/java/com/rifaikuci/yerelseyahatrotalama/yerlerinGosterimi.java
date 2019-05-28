package com.rifaikuci.yerelseyahatrotalama;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class yerlerinGosterimi extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    Marker konumum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yerlerin_gosterimi);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
       FloatingActionButton floatingActionButton = findViewById(R.id.yonOlustur);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

            }
        });

        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ulucami = new LatLng(37.717430, 30.286363);
        mMap.addMarker(new MarkerOptions().position(ulucami).title("Ulu Cami").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng hacilar = new LatLng(37.584867, 30.084486);
        mMap.addMarker(new MarkerOptions().position(hacilar).title("Hacılar Höyük").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng incirhan= new LatLng(37.478628, 30.533486);
        mMap.addMarker(new MarkerOptions().position(incirhan).title("İncir Han").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng burdurmuzesi = new LatLng(37.719492, 30.286229);
        mMap.addMarker(new MarkerOptions().position(burdurmuzesi).title("Burdur Müzesi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng doga = new LatLng(37.714039, 30.281065);
        mMap.addMarker(new MarkerOptions().position(doga).title("Doğa Tarihi Müzesi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng sagalassos = new LatLng(37.677311, 30.516952);
        mMap.addMarker(new MarkerOptions().position(sagalassos).title("Sagalassos Antik Kenti").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


        LatLng kibyra = new LatLng(37.156866, 29.499676);
        mMap.addMarker(new MarkerOptions().position(kibyra).title("Kibyra Antik Kenti").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng kremna = new LatLng(37.497568, 30.688048);
        mMap.addMarker(new MarkerOptions().position(kremna).title("Kremna Antik Kenti").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng bakibey = new LatLng(     37.714896, 30.287548);
        mMap.addMarker(new MarkerOptions().position(bakibey).title("Baki Bey Konağı").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

        LatLng tas = new LatLng(     37.717124, 30.288768);
        mMap.addMarker(new MarkerOptions().position(tas).title("Taş Oda Konağı").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

        LatLng misirlilar = new LatLng(     37.715267, 30.283913);
        mMap.addMarker(new MarkerOptions().position(misirlilar).title("Mısırlılar Evi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

        LatLng burdurgolu = new LatLng(     37.735082, 30.170443);
        mMap.addMarker(new MarkerOptions().position(burdurgolu).title("Burdur Gölü").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        LatLng golhisar = new LatLng(     37.115486, 29.600524);
        mMap.addMarker(new MarkerOptions().position(golhisar).title("Gölhisar Gölü").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        LatLng salda = new LatLng(     37.552650, 29.672964);
        mMap.addMarker(new MarkerOptions().position(salda).title("Salda Gölü").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        LatLng insuyu = new LatLng(     37.659385, 30.374563);
        mMap.addMarker(new MarkerOptions().position(insuyu).title("İnsuyu Mağarası").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(insuyu,8));




        mMap.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
            @Override
            public void onInfoWindowLongClick(Marker marker) {
                if(marker.getTitle().equals("Ulu Cami"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "ulucami");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Hacılar Höyük"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "hacilarhoyuk");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("İncir Han"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "incirhan");
                    startActivity(intent);
                }
                if(marker.getTitle().equals("Burdur Müzesi"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "burdurmuzesi");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Doğa Tarihi Müzesi"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "dogatarihmuzesi");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Sagalassos Antik Kenti"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "sagalassos");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Kibyra Antik Kenti"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "kibyra");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Kremna Antik Kenti"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "kremna");
                    startActivity(intent);
                }


                if(marker.getTitle().equals("Baki Bey Konağı"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "bakibey");
                    startActivity(intent);
                }



                if(marker.getTitle().equals("Taş Oda Konağı"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "tasoda");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Mısırlılar Evi"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "misirlilar");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Burdur Gölü"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "burdurgolu");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Gölhisar Gölü"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "golhisargolu");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("Salda Gölü"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "saldagolu");
                    startActivity(intent);
                }

                if(marker.getTitle().equals("İnsuyu Mağarası"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "insuyu");
                    startActivity(intent);
                }


                if(marker.getTitle().equals("Burdur"))
                {
                    Intent intent = new Intent(getApplicationContext(), detayAktivite.class);
                    intent.putExtra("yer", "burdur");
                    startActivity(intent);
                }
                }
        });



        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {



                LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());

                if (konumum!=null) {
                    konumum.remove();
                    konumum=null;
                }
                 konumum =mMap.addMarker(new MarkerOptions().title("Konumunuz").position(userLocation));

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
        };


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);


                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(lastLocation!= null){
                    double latitude = lastLocation.getLatitude();
                    double longitude = lastLocation.getLongitude();

                    System.out.println("lastLocation: " + lastLocation);
                    LatLng userLastLocation = new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().title("Son Konumunuz").position(userLastLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,8));

                }
                else
                {
                    LatLng burdurMerkez = new LatLng(37.5157417,30.0129201);
                    mMap.addMarker(new MarkerOptions().position(burdurMerkez).title("Burdur").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(burdurMerkez,8));
                }


            }
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            System.out.println("lastLocation: " + lastLocation);
            LatLng userLastLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().title("Son Konumunuz").position(userLastLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,8));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0) {
            if (requestCode == 1) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
