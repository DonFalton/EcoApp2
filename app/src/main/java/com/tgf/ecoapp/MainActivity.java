package com.tgf.ecoapp;

import android.os.Bundle;
import android.view.MenuInflater;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications,
                R.id.navigation_map, R.id.navigation_more, R.id.navigation_recycle) // <-- Agrega este id
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_more) {
                // inflar y mostrar el menú emergente
                PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.navigation_more));
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_more, popup.getMenu());
                popup.setOnMenuItemClickListener(popupItem -> {
                    int id = popupItem.getItemId();
                    if (id == R.id.action_messages) {
                        // Navega al fragmento de mensajes
                        navController.navigate(R.id.navigation_messages);
                        return true;
                    } else if (id == R.id.action_settings) {
                        // Navega al fragmento de ajustes
                        navController.navigate(R.id.navigation_settings);
                        return true;
                    } else if (id == R.id.action_help) {
                        // Navega al fragmento de ayuda
                        navController.navigate(R.id.navigation_help);
                        return true;
                    } else if (id == R.id.action_contact) {
                        // Navega al fragmento de contacto
                        navController.navigate(R.id.navigation_contact);
                        return true;
                    } else if (id == R.id.action_about) {
                        // Navega al fragmento "acerca de"
                        navController.navigate(R.id.navigation_about);
                        return true;
                    }
                    return false;
                });
                popup.show();
                return true;
            }
            NavigationUI.onNavDestinationSelected(item, navController);
            return false;
        });

        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}