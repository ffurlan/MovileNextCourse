package presenter;

import java.util.List;

import model.Episode;
import remote.callbacks.SeasonDetailsCallback;
import remote.client.SeasonEpisodeRemoteCaller;
import view.SeasonDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonDetailsPresenter  implements SeasonDetailsCallback {
    private SeasonDetailsView mView;
    private String mUrlBase;

    public SeasonDetailsPresenter(SeasonDetailsView view, String urlBase)
    {
        mView = view;
        mUrlBase = urlBase;
    }

    public void getSeasonEpisodeList(String series, int season)
    {
        SeasonEpisodeRemoteCaller sERC = new SeasonEpisodeRemoteCaller(this,mUrlBase);
        sERC.getSeasonEpisodeList(series, season);
    }

    @Override
    public void onListEpisodeLoaded(List<Episode> episodes) {
        mView.displayEpisodeList(episodes);
    }
}
