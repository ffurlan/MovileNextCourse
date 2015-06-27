package remote.callbacks;

import java.util.List;

import model.Season;

/**
 * Created by movile on 27/06/15.
 */
public interface SeasonsCallback {
    public void onSeasonsLoaded(List<Season> season);

}
