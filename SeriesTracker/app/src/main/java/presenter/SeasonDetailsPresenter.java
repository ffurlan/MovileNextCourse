package presenter;

import java.util.List;

import model.Episode;
import model.Season;
import remote.callbacks.SeasonDetailsCallback;
import remote.callbacks.SeasonsCallback;
import remote.client.SeasonEpisodeRemoteCaller;
import remote.client.SeasonRemoteCaller;
import remote.services.SeasonRemoteService;
import view.SeasonDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonDetailsPresenter  implements SeasonDetailsCallback, SeasonsCallback {
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

    public void getSeasons(String series)
    {
        SeasonRemoteCaller sR = new SeasonRemoteCaller(this,mUrlBase);
        sR.getAllSeasons(series);
    }

    @Override
    public void onListEpisodeLoaded(List<Episode> episodes) {
        mView.displayEpisodeList(episodes);
    }

    @Override
    public void onSeasonsLoaded(List<Season> seasons) {
        mView.displaySeasonInfo(seasons);
    }
}
