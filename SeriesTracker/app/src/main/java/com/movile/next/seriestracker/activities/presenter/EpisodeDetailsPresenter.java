package com.movile.next.seriestracker.activities.presenter;

import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.remote.callbacks.EpisodeDetailsCallback;
import com.movile.next.seriestracker.activities.remote.client.EpisodeRemoteCaller;
import com.movile.next.seriestracker.activities.view.EpisodeDetailsView;

/**
 * Created by movile on 20/06/15.
 */
public class EpisodeDetailsPresenter implements EpisodeDetailsCallback
{
    private EpisodeDetailsView mView;
    private String mUrlBase;

    public EpisodeDetailsPresenter(EpisodeDetailsView view, String urlBase)
    {
        mView = view;
        mUrlBase = urlBase;
    }

    public void getEpisodeDetails(String series, int season, int episode)
    {
        EpisodeRemoteCaller erc = new EpisodeRemoteCaller(this,mUrlBase);
        erc.getEpisodeDetails(series, season, episode);
    }

     @Override
    public void onEpisodeLoaded(Episode episode) {
        mView.displayEpisode(episode);
    }
}
