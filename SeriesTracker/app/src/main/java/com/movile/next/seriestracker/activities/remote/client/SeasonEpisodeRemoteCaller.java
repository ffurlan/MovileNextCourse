package com.movile.next.seriestracker.activities.remote.client;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.remote.callbacks.SeasonDetailsCallback;
import com.movile.next.seriestracker.activities.remote.services.SeasonEpisodesRemoteService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonEpisodeRemoteCaller {
    private SeasonDetailsCallback mCallback;
    private RestAdapter mAdapter;

    public SeasonEpisodeRemoteCaller(SeasonDetailsCallback callBack, String endpoint)
    {
        mCallback = callBack;
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public void getSeasonEpisodeList(String show, Integer season){

        SeasonEpisodesRemoteService service = mAdapter.create(SeasonEpisodesRemoteService.class);

        service.getSeasonEpisodes(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                mCallback.onListEpisodeLoaded(episodes);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
