package com.movile.next.seriestracker.activities.remote.services;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Season;
import com.movile.next.seriestracker.activities.remote.constants.ApiConfiguration;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by movile on 21/06/15.
 */
public interface SeasonRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons?extended=full,images")
    void getSeasons(
            @Path("show") String show,
            Callback<List<Season>> callback);
}
