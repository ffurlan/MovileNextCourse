package com.movile.next.seriestracker.activities.presenter;

import com.movile.next.seriestracker.activities.model.Season;
import com.movile.next.seriestracker.activities.remote.callbacks.SeasonDetailsHeaderCallback;
import com.movile.next.seriestracker.activities.remote.client.SeasonRemoteCaller;
import com.movile.next.seriestracker.activities.view.SeasonDetailsHeaderView;

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
