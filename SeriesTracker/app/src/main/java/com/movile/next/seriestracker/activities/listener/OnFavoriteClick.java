package com.movile.next.seriestracker.activities.listener;

import com.movile.next.seriestracker.activities.model.Favorite;

/**
 * Created by movile on 05/07/15.
 */
public interface OnFavoriteClick {
    public abstract void onFavoriteClick(String slug, String title);
}
