package com.luisftec.proyectoapp;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapaLatLonActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView txtLat, txtLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_lat_lon);

        // obtener referencias a los elementos de la interfaz de usuario
        txtLat = findViewById(R.id.txtLat);
        txtLon = findViewById(R.id.txtLon);

        // obtener el fragmento de mapa y esperar a que esté listo
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        // configurar el botón de búsqueda y el campo de texto de ubicación
        Button btnBuscar = findViewById(R.id.btnBuscar);
        EditText txtLocation = findViewById(R.id.txtLocation);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obtener el texto de ubicación ingresado por el usuario
                String location = txtLocation.getText().toString();
                if (!location.isEmpty()) {
                    buscarLocalizacion(location);
                } else {
                    // Mostrar un mensaje al no ingresar ningún texto
                    AlertDialog.Builder builder = new AlertDialog.Builder(MapaLatLonActivity.this);

                    // Configurar cualquier otro atributo necesario del diálogo
                    builder.setTitle("ESTA VACIO");

                    // Agregar botón de aceptar
                    builder.setPositiveButton("Aceptar", null);
                    builder.setMessage("Debes ingresar una ubicación que deseas buscar.");

                    // Crear el diálogo
                    AlertDialog dialog = builder.create();

                    // Establecer el fondo personalizado del diálogo
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogo_personalizado);

                    // Mostrar el diálogo
                    dialog.show();
                }

            }
        });
    }
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));
                actualizarCoordenadas(latLng);
            }
        });

        // Habilitar el botón de ubicación
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Mover la cámara a Perú Lima
        LatLng peruLatLng = new LatLng(-12.046374, -77.042793);
        mMap.addMarker(new MarkerOptions().position(peruLatLng).title("Veterinaria App"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peruLatLng, 11));

        actualizarCoordenadas(peruLatLng);
    }

    private void actualizarCoordenadas(LatLng latLng) {
        txtLat.setText(String.valueOf(latLng.latitude));
        txtLon.setText(String.valueOf(latLng.longitude));
    }

    // metodo que se llama cuando se realiza una busqueda de ubicacion
    private void buscarLocalizacion(String location) {
        // creamos un objeto geocoder para obtener las coordenadas de la ubicación ingresada
        Geocoder geocoder = new Geocoder(this);
        try {
            // buscamos las direcciones que coincidan con la ubicación ingresada
            List<Address> addresses = geocoder.getFromLocationName(location,1);
            // si encontramos la direccion, obtener la primera dirección
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                // agregamos un marcador en la ubicación encontrada y movemos la cámara allí
                mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                // actualizamos las coordenadas mostradas en la interfaz del usuario
                actualizarCoordenadas(latLng);
            } else {
                // No se encontró ninguna ubicación correspondiente al texto ingresado
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Ocurrió un error al buscar la ubicación
        }
    }
}
