package binarykeys.aquainfo;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double latitude;
    private Double longitude;
    private String id;
    private Intent i;
    private Bundle b;
    private AlertDialog ad;
    private AlertDialog.Builder ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ab=new AlertDialog.Builder(this);
        ab.setTitle("Wait").setMessage("Loading Map").setCancelable(false);
        ad=ab.create();
        ad.show();
        i=getIntent();
        b=i.getExtras();
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
        ad.hide();
        id=b.get("id").toString();
        latitude=Double.parseDouble(b.get("latitude").toString());
        longitude=Double.parseDouble(b.get("longitude").toString());
        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(loc).title("Marker on "+id.trim()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }
}
