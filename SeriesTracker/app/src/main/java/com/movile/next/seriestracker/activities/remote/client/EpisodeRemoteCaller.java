package com.movile.next.seriestracker.activities.remote.client;

import com.movile.next.seriestracker.activities.model.Episode;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import com.movile.next.seriestracker.activities.remote.services.EpisodeRemoteService;
import com.movile.next.seriestracker.activities.remote.callbacks.EpisodeDetailsCallback;

/**
 * Created by movile on 20/06/15.
 */
public class EpisodeRemoteCaller {

    private EpisodeDetailsCallback mCallback;
    private RestAdapter mAdapter;

    public EpisodeRemoteCaller(EpisodeDetailsCallback callBack, String endpoint)
    {
        mCallback = callBack;
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public void getEpisodeDetails(String show, Integer season, Integer episode){


        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mCallback.onEpisodeLoaded(episode);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
