package com.tgf.ecoapp.ui.map;

import android.Manifest;
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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.tgf.ecoapp.R;

import org.imperiumlabs.geofirestore.GeoFirestore;
import org.imperiumlabs.geofirestore.GeoQuery;
import org.imperiumlabs.geofirestore.listeners.GeoQueryEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    // Define una instancia de GoogleMap llamada mMap.
    private GoogleMap mMap;
    // Instancia para la conexión a Firestore.
    private FirebaseFirestore db;
    // Listas de los tipos de contenedores y los barrios.
    private List<String> contenedorTypes = Arrays.asList("ENVASES", "ORGANICA", "PAPEL-CARTON", "RESTO", "VIDRIO");
    private List<String> barrios = Arrays.asList("CARABANCHEL", "CENTRO", "LATINA", "MONCLOA-ARAVACA", "ARGANZUELA");
    // Mapa para guardar los marcadores por cada contenedor.
    private Map<String, Marker> markers = new HashMap<>();
    // Mapa para guardar todos los marcadores.
    private Map<Marker, String> allMarkers = new HashMap<>();
    // Define los colores para cada tipo de contenedor.
    private Map<String, Float> contenedorColors = new HashMap<String, Float>() {{
        put("ENVASES", BitmapDescriptorFactory.HUE_YELLOW); // Amarillo
        put("PAPEL-CARTON", BitmapDescriptorFactory.HUE_BLUE); // Azul
        put("ORGANICA", 30F); // Marrón
        put("VIDRIO", BitmapDescriptorFactory.HUE_GREEN); // Verde
        put("RESTO", BitmapDescriptorFactory.HUE_ORANGE); // Naranja
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla la vista para este fragmento.
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        // Obtiene el SupportMapFragment y establece el callback para cuando el mapa esté listo.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Inicializa Firestore.
        db = FirebaseFirestore.getInstance();

        return root;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Guarda la referencia al mapa de Google.
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

        // Configura las opciones de UI para el mapa.
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMapToolbarEnabled(true);

        // Mueve la cámara a la ubicación de Madrid.
        LatLng madrid = new LatLng(40.416775, -3.703790);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 15));

        // Agregar contenedores desde Firestore
        addContainersFromFirestore("ENVASES", BitmapDescriptorFactory.HUE_YELLOW); // Amarillo
        addContainersFromFirestore("PAPEL-CARTON", BitmapDescriptorFactory.HUE_BLUE); // Azul
        addContainersFromFirestore("ORGANICA", 30); // Marrón
        addContainersFromFirestore("VIDRIO", BitmapDescriptorFactory.HUE_GREEN); // Verde
        addContainersFromFirestore("RESTO", BitmapDescriptorFactory.HUE_ORANGE); // Naranja

        // Crea un GeoQuery para obtener los contenedores en la ubicación actual.
        mMap.setOnCameraIdleListener(() -> {
            LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;

            // Obtiene las coordenadas de la esquina inferior izquierda y superior derecha.
            GeoPoint sw = new GeoPoint(bounds.southwest.latitude, bounds.southwest.longitude);
            GeoPoint ne = new GeoPoint(bounds.northeast.latitude, bounds.northeast.longitude);

            for (String contenedorType : contenedorTypes) {
                for (String barrio : barrios) {
                    // Crea una referencia a GeoFirestore.
                    GeoFirestore geoFirestore = new GeoFirestore(db.collection("ContenedoresLite").document(contenedorType).collection(barrio));

                    // Crea una GeoQuery en la ubicación actual.
                    GeoQuery geoQuery = geoFirestore.queryAtLocation(new GeoPoint((sw.getLatitude() + ne.getLatitude()) / 2, (sw.getLongitude() + ne.getLongitude()) / 2), getDistance(sw, ne) / 2);

                    // Agrega un GeoQueryEventListener a la GeoQuery.
                    geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                        @Override
                        public void onKeyEntered(String key, GeoPoint location) {
                            // Remueve el marcador si ya existe.
                            if (markers.containsKey(key)) {
                                Marker marker = markers.get(key);
                                if (marker != null) marker.remove();
                            }

                            // Agrega un nuevo marcador.
                            LatLng containerLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(containerLatLng)
                                    .title(key)
                                    // Busca el color en el mapa de colores.
                                    .icon(BitmapDescriptorFactory.defaultMarker(contenedorColors.get(contenedorType))));

                            // Guarda el marcador en el mapa.
                            markers.put(key, marker);
                        }

                        @Override
                        public void onKeyExited(String key) {
                            // Cuando un key sale del área de la query, remueve el marcador.
                            if (markers.containsKey(key)) {
                                Marker marker = markers.get(key);
                                if (marker != null) marker.remove();
                                markers.remove(key);
                            }
                        }

                        @Override
                        public void onKeyMoved(String key, GeoPoint location) {
                            // Actualiza la posición del marcador.
                            if (markers.containsKey(key)) {
                                Marker marker = markers.get(key);
                                if (marker != null) {
                                    marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
                                }
                            }
                        }

                        @Override
                        public void onGeoQueryReady() {
                            // Se ha cargado todos los datos iniciales y se han disparado los eventos!
                        }

                        @Override
                        public void onGeoQueryError(Exception error) {
                            // Se muestra un mensaje de error si algo sale mal.
                            Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            // Remueve marcadores que están fuera de la vista.
            Iterator<Map.Entry<String, Marker>> iterator = markers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Marker> entry = iterator.next();
                if (!bounds.contains(entry.getValue().getPosition())) {
                    entry.getValue().remove();
                    iterator.remove();
                }
            }
        });
    }

    // Calcula la distancia entre dos GeoPoints.
    private double getDistance(GeoPoint p1, GeoPoint p2) {
        double dLat = Math.toRadians(p2.getLatitude() - p1.getLatitude());
        double dLng = Math.toRadians(p2.getLongitude() - p1.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(p1.getLatitude())) * Math.cos(Math.toRadians(p2.getLatitude()))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371 * c; // Convert to kilometers
        return distance;
    }


    // Este método es llamado cuando el usuario ha concedido o denegado los permisos solicitados.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(getContext(), "Location permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Este método personalizado añade marcadores al mapa desde la base de datos Firestore
    // basándose en el tipo de contenedor y el color que se deben usar.
    private void addContainersFromFirestore(String contenedorType, float hueColor) {
        db.collection("ContenedoresLite")
                .document(contenedorType)
                .collection("g") // Changed from collectionGroup to collection.
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> documents = task.getResult().getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Double latitud = document.getDouble("LATITUD");
                            Double longitud = document.getDouble("LONGITUD");
                            String nombre = document.getString("Nombre");

                            if (latitud != null && longitud != null && nombre != null) {
                                LatLng containerLatLng = new LatLng(latitud, longitud);
                                Marker marker = mMap.addMarker(new MarkerOptions()
                                        .position(containerLatLng)
                                        .title(nombre)
                                        .icon(BitmapDescriptorFactory.defaultMarker(hueColor)));
                                allMarkers.put(marker, document.getId());
                            }
                        }
                    } else {
                        // Log: Error getting documents
                    }
                });
    }
}