package com.example.tiendaeco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // Tres ubicaciones predefinidas (Colombia)
    private final LatLng ubicacion1 = new LatLng(4.6097, -74.0817);  // Bogotá
    private final LatLng ubicacion2 = new LatLng(3.4516, -76.5320);  // Cali
    private final LatLng ubicacion3 = new LatLng(10.3932, -75.4832); // Cartagena

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Obtener el fragmento del mapa y notificar cuando esté listo
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Configurar botones
        setupButtons();
    }

    private void setupButtons() {
        Button btnLocation1 = findViewById(R.id.btnLocation1);
        Button btnLocation2 = findViewById(R.id.btnLocation2);
        Button btnLocation3 = findViewById(R.id.btnLocation3);

        btnLocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLocation(ubicacion1, "Bogotá");
            }
        });

        btnLocation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLocation(ubicacion2, "Cali");
            }
        });

        btnLocation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLocation(ubicacion3, "Cartagena");
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Habilitar controles de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Mover a la primera ubicación por defecto
        moveToLocation(ubicacion1, "Bogotá");
    }

    // Función para mover el mapa a una ubicación específica
    private void moveToLocation(LatLng location, String title) {
        mMap.clear(); // Limpiar marcadores anteriores

        mMap.addMarker(new MarkerOptions().position(location).title(title)); // Añadir marcador

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f)); // Mover cámara
    }
}
