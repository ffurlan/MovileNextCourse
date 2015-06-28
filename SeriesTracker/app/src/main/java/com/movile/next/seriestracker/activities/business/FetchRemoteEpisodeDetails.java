package com.movile.next.seriestracker.activities.business;

import android.content.Context;
import android.util.Log;

import com.movile.next.seriestracker.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.converter.ModelConverter;

/**
 * Created by movile on 14/06/15.
 */
public class FetchRemoteEpisodeDetails {

    public Episode Get(Context context, String url) {
        Episode episode = null;
        InputStreamReader reader = null;
        try {
            HttpURLConnection connection = configureConnection(context, url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(this.getClass().getName(), "Error loading remote content", e);
        } finally {
// Release InputStreamReader if used
            return episode;
        }
    }


    public HttpURLConnection configureConnection(Context context, String url) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setReadTimeout(context.getResources().getInteger(R.integer.api_timeout_read));
        connection.setConnectTimeout(context.getResources().getInteger(R.integer.api_timeout_connect));
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("trakt-api-version", context.getResources().getString(R.string.api_version));
        connection.setRequestProperty("trakt-api-key", context.getResources().getString(R.string.api_key));

        return connection;
    }
}
