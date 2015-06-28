package com.movile.next.seriestracker.activities.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.activity.ShowActivity;
import com.movile.next.seriestracker.activities.activity.ShowDetailsActivity;
import com.movile.next.seriestracker.activities.model.ShowUpdate;

/**
 * Created by movile on 28/06/15.
 */
public class ShowUpdateReceiver extends BroadcastReceiver {

    public static final String EXTRA_UPDATE = "EXTRA_UPDATE";
    private static ShowUpdate mShowUpdate;

    public void onReceive(Context context, Intent intent) {
        GetExtrasFromBundle(intent);

        Intent intentNot = new Intent(context, ShowDetailsActivity.class);
        intentNot.putExtra(ShowDetailsActivity.EXTRA_SHOW_FULL_NAME, mShowUpdate.show());
        intentNot.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ShowActivity.class);
        stackBuilder.addNextIntent(intentNot);

        PendingIntent action = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mShowUpdate.title())
                .setContentText(mShowUpdate.message())
                .setContentIntent(action)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mShowUpdate.message()));

        Notification notification = builder.build();

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, notification);
    }

    public void GetExtrasFromBundle(Intent intent)
    {
        mShowUpdate = (ShowUpdate)intent.getExtras().getSerializable(EXTRA_UPDATE);
    }


}
