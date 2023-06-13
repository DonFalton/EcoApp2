package com.tgf.ecoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tgf.ecoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Configurar los identificadores de los elementos de menú como destinos de nivel superior.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications,
                R.id.navigation_map, R.id.navigation_more, R.id.navigation_recycle)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Escucha el evento de selección de elementos en la barra de navegación inferior
        navView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_more) {
                // Log message
                Log.d(TAG, "Menú emergente abierto");
                // Si se selecciona el elemento "Más"
                // Inflar y mostrar el menú emergente
                View view = navView.findViewById(R.id.navigation_more);  // Obtén la vista del elemento de menú correspondiente
                PopupMenu popup = new PopupMenu(MainActivity.this, view);// Úsala como el ancla del menú emergente
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_more, popup.getMenu());
                popup.setOnMenuItemClickListener(popupItem -> {
                    int id = popupItem.getItemId();
                    if (id == R.id.action_messages) {
                        // Si se selecciona "Mensajes"
                        // Navegar al fragmento de mensajes
                        navController.navigate(R.id.navigation_messages);
                        return true;
                    } else if (id == R.id.action_settings) {
                        // Si se selecciona "Ajustes"
                        // Navegar al fragmento de ajustes
                        navController.navigate(R.id.navigation_settings);
                        return true;
                    } else if (id == R.id.action_help) {
                        // Si se selecciona "Ayuda"
                        // Navegar al fragmento de ayuda
                        navController.navigate(R.id.navigation_help);
                        return true;
                    } else if (id == R.id.action_contact) {
                        // Si se selecciona "Contacto"
                        // Navegar al fragmento de contacto
                        navController.navigate(R.id.navigation_contact);
                        return true;
                    } else if (id == R.id.action_about) {
                        // Si se selecciona "Acerca de"
                        // Navegar al fragmento "Acerca de"
                        navController.navigate(R.id.navigation_about);
                        return true;
                    }
                    return false;
                });
                popup.show();
                return true;
            } else {
                // Si se selecciona otro elemento de la# Continuing the Java code from the previous message
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
            }
        });

        // Configura la barra de acción con el botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Maneja el clic en el botón de retroceso de la barra de acción
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
