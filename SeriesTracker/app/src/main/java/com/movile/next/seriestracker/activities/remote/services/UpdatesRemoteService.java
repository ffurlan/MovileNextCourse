package com.movile.next.seriestracker.activities.remote.services;

import com.movile.next.seriestracker.activities.model.ShowUpdate;
import retrofit.http.GET;

/**
 * Created by movile on 28/06/15.
 */
public interface UpdatesRemoteService {

    @GET("/latestUpdate.json")
    ShowUpdate getLatest();
}
