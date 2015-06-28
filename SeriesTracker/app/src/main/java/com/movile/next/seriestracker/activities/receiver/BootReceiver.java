package com.movile.next.seriestracker.activities.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.movile.next.seriestracker.activities.services.UpdateService;

/**
 * Created by movile on 28/06/15.
 */
public class BootReceiver extends BroadcastReceiver {
    //private static final long INTERVAL = 30*60*1000;
    private static final long INTERVAL = 10*60;


    @Override
    public void onReceive(Context context, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateService.class), 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, INTERVAL, pendingIntent);
    }
}
