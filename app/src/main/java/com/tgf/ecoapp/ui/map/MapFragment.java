package com.tgf.ecoapp.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tgf.ecoapp.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento de mapa
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        // Obtener el fragmento de mapa de soporte
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); // Configurar el callback cuando el mapa esté listo

        return root; // Devolver la vista raíz
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Agregar un marcador en Madrid
        LatLng madrid = new LatLng(40.416775, -3.703790);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid")); // Agregar un marcador en Madrid, pero actualmente no es necesario
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 15)); // Hacer zoom a Madrid con un nivel de zoom de 15
    }

}