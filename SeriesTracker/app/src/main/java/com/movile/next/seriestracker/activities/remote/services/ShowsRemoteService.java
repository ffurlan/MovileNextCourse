package com.movile.next.seriestracker.activities.remote.services;

import java.util.List;

import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.remote.constants.ApiConfiguration;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by movile on 27/06/15.
 */
public interface ShowsRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/popular?limit=50&extended=full,images")
    void getShows(
            Callback<List<Show>> callback);
}