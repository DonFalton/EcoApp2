package com.tgf.ecoapp.ui.notifications;

/**
 * Created by Martin B. on 15/6/23.
 * martin.blazquez.dam@gmail.com
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tgf.ecoapp.MainActivity;
import com.tgf.ecoapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class FraseDelDiaService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private DatabaseReference mDatabase;

    public static final String CHANNEL_ID = "fraseDelDiaChannel";

    public FraseDelDiaService() {
        super("FraseDelDiaService");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("FRASES");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Obtén el día actual
        String day = new SimpleDateFormat("d", Locale.getDefault()).format(new Date());

        // Busca la frase del día en Firebase
        mDatabase.child("Doc_" + day).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Obtén la frase y muestra la notificación
                String frase = dataSnapshot.child("Frase").getValue().toString();
                showNotification(frase);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log error.
            }
        });
    }

    private void showNotification(String frase) {
        // Crea una intención para abrir tu aplicación cuando el usuario haga clic en la notificación
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Frase del día")
                .setContentText(frase)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(contentIntent)
                .setAutoCancel(true);


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}