package com.movile.next.seriestracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.movile.next.seriestracker.base.BaseNavigationToolbarActivity;

import java.util.List;

import adapters.ShowGridAdapter;
import listener.OnShowClick;
import model.Show;
import presenter.ShowPresenter;
import view.ShowsView;

/**
 * Created by movile on 27/06/15.
 */
public class ShowActivity  extends BaseNavigationToolbarActivity implements ShowsView, OnShowClick {

    private static ShowGridAdapter mAdapter;
    private static ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_activity);

        configureToolbar();

        showPresenter = new ShowPresenter(this, getString(R.string.api_url_base));
        showPresenter.getShows();

        GridView gridview = (GridView) findViewById(R.id.gridShows);
        mAdapter = new ShowGridAdapter(this, this);
        gridview.setAdapter(mAdapter);

        getSupportActionBar().setTitle("Series Tracker");
    }


    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOWNAME, show.ids().slug());
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW_FULL_NAME, show.title());
        startActivity(intent);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.UpdateViewData(shows);
    }
}
