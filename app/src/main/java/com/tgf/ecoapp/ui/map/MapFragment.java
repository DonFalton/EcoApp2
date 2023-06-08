package com.tgf.ecoapp.ui.map;

import android.Manifest;  // <-- Agrega esta importación
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.tgf.ecoapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento de mapa
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        // Obtener el fragmento de mapa de soporte
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); // Configurar el callback cuando el mapa esté listo

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        return root; // Devolver la vista raíz
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable Zoom Controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
       // mMap.setPadding(0, 0, 0, 100);  // Move the zoom controls up

        // Check if we have the location permission
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            mMap.setMyLocationEnabled(true);
        } else {
            // We do not have the permission, request it
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Show the location button
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Set location button position
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMapToolbarEnabled(true);

        LatLng madrid = new LatLng(40.416775, -3.703790);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 15));

        // Read data from Firestore and add markers
        db.collection("ContenedoresLite")
                .document("ENVASES")
                .collection("CENTRO")
                .document("167909")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Double latitud = document.getDouble("LATITUD");
                            Double longitud = document.getDouble("LONGITUD");
                            String nombre = document.getString("Nombre");

                            if (latitud != null && longitud != null && nombre != null) {
                                LatLng containerLatLng = new LatLng(latitud, longitud);
                                mMap.addMarker(new MarkerOptions()
                                        .position(containerLatLng)
                                        .title(nombre)
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))); // Set marker color to yellow
                            } else {
                                // Log: One of the required fields is null
                            }
                        } else {
                            // Log: No such document
                        }
                    } else {
                        // Log: Error getting document
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied
                Toast.makeText(getContext(), "Location permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
