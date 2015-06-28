package com.movile.next.seriestracker.activities.remote.callbacks;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Season;

/**
 * Created by movile on 27/06/15.
 */
public interface SeasonsCallback {
    public void onSeasonsLoaded(List<Season> season);

}
