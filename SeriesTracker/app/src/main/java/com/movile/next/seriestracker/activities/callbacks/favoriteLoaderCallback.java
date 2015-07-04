package com.movile.next.seriestracker.activities.callbacks;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.next.seriestracker.activities.listener.OnFavoriteListener;
import com.movile.next.seriestracker.activities.loader.FavoriteLoader;
import com.movile.next.seriestracker.activities.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public class favoriteLoaderCallback implements LoaderManager.LoaderCallbacks<Favorite>{
    private OnFavoriteListener mListener;
    private Context mContext;
    private String mSlug;
    private String mTitle;
    private Boolean mReadOnly;

    public favoriteLoaderCallback(OnFavoriteListener listener,
                                  Context context,
                                  String slug,
                                  String title,
                                  Boolean readOnly)
    {
        mListener = listener;
        mContext = context;
        mSlug = slug;
        mTitle = title;
        mReadOnly = readOnly;
    }

    @Override
    public Loader<Favorite> onCreateLoader(int id, Bundle args) {
        return new FavoriteLoader(mContext, mSlug, mTitle, mReadOnly);
    }

    @Override
    public void onLoadFinished(Loader<Favorite> loader, Favorite data) {
        mListener.onFavoriteLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Favorite> loader) {

    }
}
