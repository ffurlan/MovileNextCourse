package presenter;

import java.util.List;

import model.Show;
import remote.callbacks.ShowCallback;
import remote.client.ShowsRemoteCaller;
import view.ShowsView;

/**
 * Created by movile on 27/06/15.
 */
public class ShowPresenter implements ShowCallback {
    private ShowsView mView;
    private String mUrlBase;

    public ShowPresenter(ShowsView view, String urlBase) {
        mView = view;
        mUrlBase = urlBase;
    }

    public void getShows() {
        ShowsRemoteCaller sRC = new ShowsRemoteCaller(this, mUrlBase);
        sRC.getShows();
    }

    @Override
    public void onShowsLoaded(List<Show> shows) {
        mView.displayShows(shows);
    }
}
