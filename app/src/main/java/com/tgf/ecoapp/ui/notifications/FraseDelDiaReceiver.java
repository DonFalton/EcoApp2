package com.tgf.ecoapp.ui.notifications;

/**
 * Created by Martin B. on 15/6/23.
 * martin.blazquez.dam@gmail.com
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FraseDelDiaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, FraseDelDiaService.class);
        context.startService(i);
    }
}

