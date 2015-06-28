package com.movile.next.seriestracker.activities.presenter;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.Season;
import com.movile.next.seriestracker.activities.remote.callbacks.SeasonDetailsCallback;
import com.movile.next.seriestracker.activities.remote.callbacks.SeasonsCallback;
import com.movile.next.seriestracker.activities.remote.client.SeasonEpisodeRemoteCaller;
import com.movile.next.seriestracker.activities.remote.client.SeasonRemoteCaller;
import com.movile.next.seriestracker.activities.view.SeasonDetailsView;

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
