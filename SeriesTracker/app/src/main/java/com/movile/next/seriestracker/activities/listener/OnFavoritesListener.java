package com.movile.next.seriestracker.activities.listener;


import android.database.Cursor;

/**
 * Created by movile on 05/07/15.
 */
public interface OnFavoritesListener {
    public void onFavoritesLoaded(Cursor cursor);
}
