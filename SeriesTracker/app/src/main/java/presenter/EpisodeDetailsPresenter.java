package presenter;

import model.Episode;
import remote.callbacks.EpisodeDetailsCallback;
import remote.server.EpisodeRemoteCaller;
import view.EpisodeDetailsView;

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
