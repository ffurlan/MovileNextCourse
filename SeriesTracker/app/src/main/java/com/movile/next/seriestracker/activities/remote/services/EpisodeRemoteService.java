package com.movile.next.seriestracker.activities.remote.services;

import com.movile.next.seriestracker.activities.model.Episode;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import com.movile.next.seriestracker.activities.remote.constants.ApiConfiguration;

/**
 * Created by movile on 20/06/15.
 */
public interface EpisodeRemoteService {
    @Headers({
        "trakt-api-version: " + ApiConfiguration.API_VERSION,
        "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") Integer season,
            @Path("episode") Integer episode,
            Callback<Episode> callback);
}
