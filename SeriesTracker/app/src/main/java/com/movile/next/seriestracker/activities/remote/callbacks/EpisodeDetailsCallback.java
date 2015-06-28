package com.movile.next.seriestracker.activities.remote.callbacks;

import com.movile.next.seriestracker.activities.model.Episode;

/**
 * Created by movile on 20/06/15.
 */
public interface EpisodeDetailsCallback {
    public void onEpisodeLoaded(Episode episode);
}
