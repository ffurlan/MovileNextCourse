package com.movile.next.seriestracker.activities.presenter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;

import com.movile.next.seriestracker.activities.callbacks.favoritesLoaderCallback;
import com.movile.next.seriestracker.activities.listener.OnFavoritesListener;
import com.movile.next.seriestracker.activities.view.FavoritesView;


/**
 * Created by movile on 05/07/15.
 */
public class FavoritesPresenter implements OnFavoritesListener {
    private static FavoritesView mView;
    private static Context mContext;

    public FavoritesPresenter(FavoritesView view, Context context)
    {
        mView = view;
        mContext = context;
    }

    public void getFavorites(LoaderManager loader)
    {
        loader.initLoader(
                0, null, new favoritesLoaderCallback(this, mContext)
        ).forceLoad();

    }

    @Override
    public void onFavoritesLoaded(Cursor cursor) {
        mView.displayFavorites(cursor);
    }
}
