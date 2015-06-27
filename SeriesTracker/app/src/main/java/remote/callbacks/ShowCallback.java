package remote.callbacks;

import java.util.List;

import model.Show;

/**
 * Created by movile on 27/06/15.
 */
public interface ShowCallback {
    public void onShowsLoaded(List<Show> shows);
}
