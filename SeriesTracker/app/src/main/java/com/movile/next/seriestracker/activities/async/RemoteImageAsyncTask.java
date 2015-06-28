package com.movile.next.seriestracker.activities.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import com.movile.next.seriestracker.activities.listener.OnEpisodeImageListener;

public class RemoteImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private OnEpisodeImageListener mListener;


    public RemoteImageAsyncTask(OnEpisodeImageListener listener)
    {
        mListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            Log.e(this.getClass().getName(), "Error fetching image from " + url, e);
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mListener.onLoadImage(bitmap);
    }
}

