package com.tgf.ecoapp;
/**
 * Created by Martin B. on 8/6/23.
 * martin.blazquez.dam@gmail.com
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Establecer el tiempo de duración del splash screen en milisegundos
        int splashScreenDuration = 3000; // Por ejemplo, 3 segundos

        // Crear un objeto Handler para manejar la ejecución del código después del tiempo de duración
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Código para ejecutar después del tiempo de duración

                // Iniciar la siguiente actividad después del splash screen
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);

                // Cerrar la actividad del splash screen
                finish();
            }
        }, splashScreenDuration);
    }
}
