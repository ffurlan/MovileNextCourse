package com.movile.next.seriestracker.activities.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.movile.next.seriestracker.R;

import com.movile.next.seriestracker.activities.model.ShowUpdate;
import com.movile.next.seriestracker.activities.receiver.ShowUpdateReceiver;
import com.movile.next.seriestracker.activities.remote.client.UpdateRemoteCaller;
import com.movile.next.seriestracker.activities.util.FormatUtil;

import java.util.Date;

/**
 * Created by movile on 28/06/15.
 */
public class UpdateService extends IntentService {

    public UpdateService() { super("Teste"); }

    private static final String KEY_LAST_UPDATE = "LAST_UPDATE";
    private static ShowUpdate mShowUpdate;
    private static SharedPreferences mPreferences;

    @Override
    protected void onHandleIntent(Intent intent) {
        UpdateRemoteCaller urC = new UpdateRemoteCaller(getString(R.string.api_url_updates));
        mShowUpdate = urC.getLatest();

        mPreferences = getApplicationContext().getSharedPreferences(KEY_LAST_UPDATE, getApplicationContext().MODE_PRIVATE);

        // GET VALUE
        String lastDate = mPreferences.getString(KEY_LAST_UPDATE, null);
        FormatUtil fd = new FormatUtil();
        Date currentDate = fd.formatDate(mShowUpdate.date());
        Date savedDate = fd.formatDate(lastDate);

        if (lastDate == null || savedDate.compareTo(currentDate) != 0)
        {
            SaveSharedPreference(mShowUpdate.date());
            SendBroadCast();
        }
    }

    private void SendBroadCast()
    {
        Intent newIntent = new Intent("com.movile.next.seriestracker.action.SHOW_UPDATE");
        newIntent.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, mShowUpdate);
        sendBroadcast(newIntent);
    }

    private void SaveSharedPreference(String formatedDate)
    {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_LAST_UPDATE, formatedDate);
        editor.commit();
    }
}
