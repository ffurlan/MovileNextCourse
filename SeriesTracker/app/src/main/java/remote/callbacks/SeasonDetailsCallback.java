package remote.callbacks;

import java.util.List;

import model.Episode;

/**
 * Created by movile on 21/06/15.
 */
public interface SeasonDetailsCallback {
    public void onListEpisodeLoaded(List<Episode> episode);
}
