package com.movile.next.seriestracker.activities.remote.callbacks;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Show;

/**
 * Created by movile on 27/06/15.
 */
public interface ShowCallback {
    public void onShowsLoaded(List<Show> shows);
}
