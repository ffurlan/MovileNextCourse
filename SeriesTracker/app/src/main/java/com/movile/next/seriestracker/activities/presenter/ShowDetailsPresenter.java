package com.movile.next.seriestracker.activities.presenter;

import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.remote.callbacks.ShowDetailsCallback;
import com.movile.next.seriestracker.activities.remote.client.ShowRemoteCaller;
import com.movile.next.seriestracker.activities.view.ShowDetailsView;

/**
 * Created by movile on 27/06/15.
 */
public class ShowDetailsPresenter implements ShowDetailsCallback {
    private ShowDetailsView mView;
    private String mUrlBase;

    public ShowDetailsPresenter(ShowDetailsView view, String urlBase)
    {
        mView = view;
        mUrlBase = urlBase;
    }

    public void getShowInformation(String show)
    {
        ShowRemoteCaller sRC = new ShowRemoteCaller(this,mUrlBase);
        sRC.getShowInformation(show);
    }

    @Override
    public void onShowLoaded(Show show) {
        mView.displayShowInformation(show);
    }
}
