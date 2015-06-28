package com.movile.next.seriestracker.activities.remote.client;

import com.movile.next.seriestracker.activities.model.ShowUpdate;
import com.movile.next.seriestracker.activities.remote.services.UpdatesRemoteService;
import retrofit.RestAdapter;


/**
 * Created by movile on 28/06/15.
 */
public class UpdateRemoteCaller {
    private RestAdapter mAdapter;

    public UpdateRemoteCaller(String endpoint) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public ShowUpdate getLatest() {

        UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);

        return service.getLatest();
    }
}
