package presenter;

import model.Season;
import remote.callbacks.SeasonDetailsHeaderCallback;
import remote.client.SeasonEpisodeRemoteCaller;
import remote.client.SeasonRemoteCaller;
import view.SeasonDetailsHeaderView;
import view.SeasonDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonDetailsHeaderPresenter implements SeasonDetailsHeaderCallback {
    private SeasonDetailsHeaderView mView;
    private String mUrlBase;

    public SeasonDetailsHeaderPresenter(SeasonDetailsHeaderView view, String urlBase)
    {
        mView = view;
        mUrlBase = urlBase;
    }

    public void getSeasonDetails(String series, int season)
    {
        SeasonRemoteCaller sERC = new SeasonRemoteCaller(this,mUrlBase, season);
        sERC.getSeasons(series);
    }

    @Override
    public void onSeasonLoaded(Season season) {
        mView.displaySeasonHeader(season);

    }
}
