package com.movile.next.seriestracker.activities.callbacks;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.movile.next.seriestracker.activities.listener.OnFavoritesListener;
import com.movile.next.seriestracker.activities.loader.FavoritesLoader;

/**
 * Created by movile on 05/07/15.
 */
public class favoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private OnFavoritesListener mListener;
    private Context mContext;

    public favoritesLoaderCallback(OnFavoritesListener listener, Context context)
    {
        mListener = listener;
        mContext = context;
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new FavoritesLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListener.onFavoritesLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
