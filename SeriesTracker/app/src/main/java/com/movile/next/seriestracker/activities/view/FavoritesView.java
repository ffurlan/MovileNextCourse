package com.movile.next.seriestracker.activities.view;

import android.database.Cursor;

import com.movile.next.seriestracker.activities.model.Favorite;

/**
 * Created by movile on 05/07/15.
 */
public interface FavoritesView {
    void displayFavorites(Cursor cursor);
}
