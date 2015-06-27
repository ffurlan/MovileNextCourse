package view;

import java.util.List;

import model.Episode;
import model.Season;

/**
 * Created by movile on 21/06/15.
 */
public interface SeasonDetailsView {
    void displayEpisodeList(List<Episode> episode);
    void displaySeasonInfo(List<Season> seasons);
}
