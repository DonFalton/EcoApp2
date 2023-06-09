package com.tgf.ecoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
            int id = item.getItemId();
            if (id == R.id.navigation_more) {
                // Log message
                Log.d(TAG, "Menú emergente abierto");
                // Si se selecciona el elemento "Más"
                // Inflar y mostrar el menú emergente
                View view = navView.findViewById(R.id.navigation_more);  // Obtén la vista del elemento de menú correspondiente
                PopupMenu popup = new PopupMenu(MainActivity.this, view); // Úsala como el ancla del menú emergente
                popup.getMenuInflater().inflate(R.menu.menu_more, popup.getMenu());
                popup.setOnMenuItemClickListener(popupItem -> {
                    int popupId = popupItem.getItemId();
                    if (popupId == R.id.action_messages) {
                        // Si se selecciona "Mensajes"
                        // Navegar al fragmento de mensajes
                        navController.navigate(R.id.navigation_messages);
                        return true;
                    } else if (popupId == R.id.action_settings) {
                        // Si se selecciona "Ajustes"
                        // Navegar al fragmento de ajustes
                        navController.navigate(R.id.navigation_settings);
                        return true;
                    } else if (popupId == R.id.action_help) {
                        // Si se selecciona "Ayuda"
                        // Navegar al fragmento de ayuda
                        navController.navigate(R.id.navigation_help);
                        return true;
                    } else if (popupId == R.id.action_contact) {
                        // Si se selecciona "Contacto"
                        // Navegar al fragmento de contacto
                        navController.navigate(R.id.navigation_contact);
                        return true;
                    } else if (popupId == R.id.action_about) {
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
                // Si se selecciona otro elemento de la barra de navegación inferior
                // Utilizar la librería de NavigationUI para manejar la navegación
                return NavigationUI.onNavDestinationSelected(item, navController);
            }
        });

        // La línea a continuación está comentada para evitar la anulación de la configuración del listener
        // NavigationUI.setupWithNavController(binding.navView, navController);
    }
}
