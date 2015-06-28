package com.movile.next.seriestracker.activities.view;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.Season;

/**
 * Created by movile on 21/06/15.
 */
public interface SeasonDetailsView {
    void displayEpisodeList(List<Episode> episode);
    void displaySeasonInfo(List<Season> seasons);
}
