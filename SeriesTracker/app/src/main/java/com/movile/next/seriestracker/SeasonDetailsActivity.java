package com.movile.next.seriestracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.base.BaseNavigationToolbarActivity;

import java.text.MessageFormat;
import java.util.List;

import adapters.EpisodeListAdapter;
import listener.OnEpisodeClick;
import model.Episode;
import model.Images;
import model.Season;
import presenter.SeasonDetailsHeaderPresenter;
import presenter.SeasonDetailsPresenter;
import view.SeasonDetailsHeaderView;
import view.SeasonDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnEpisodeClick, SeasonDetailsHeaderView {

    private static SeasonDetailsPresenter seasonDetailsPresenter;
    private static SeasonDetailsHeaderPresenter seasonDetailsHeaderPresenter;
    private static EpisodeListAdapter mAdapter;
    private static View mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        showLoading();
        configureToolbar();

        ListView view = (ListView) findViewById(R.id.list_view);
        mHeaderView = getLayoutInflater().inflate(R.layout.season_details_header, null);
        view.addHeaderView(mHeaderView);
        mAdapter = new EpisodeListAdapter(this, this);
        view.setAdapter(mAdapter);


        //region MVP
        seasonDetailsPresenter = new SeasonDetailsPresenter(this, getString(R.string.api_url_base));
        seasonDetailsPresenter.getSeasonEpisodeList("house", 2);

        seasonDetailsHeaderPresenter = new SeasonDetailsHeaderPresenter(this, getString(R.string.api_url_base));
        seasonDetailsHeaderPresenter.getSeasonDetails("house", 2);


        getSupportActionBar().setTitle(MessageFormat.format("Season{0}", "2"));
    }

    @Override
    public void displayEpisodeList(List<Episode> episodes) {
        mAdapter.UpdateViewData(episodes);
    }

    @Override
    public void onEpisodeClick(Episode episode) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODENUMBER, episode.number());
        startActivity(intent);

    }

    @Override
    public void displaySeasonHeader(Season season) {
        TextView txtSeasonRatting = (TextView) findViewById(R.id.text_seasonRating);
        txtSeasonRatting.setText(season.rating().toString());

        ImageView image_season_small = (ImageView) findViewById(R.id.image_season);
        String url = season.images().thumb().get(Images.ImageSize.FULL);
        Glide.with(getApplicationContext())
                .load(url)
                .centerCrop()
                .into(image_season_small);

        ImageView image_season_large = (ImageView) findViewById(R.id.image_seasonTitle);
        url = season.images().poster().get(Images.ImageSize.THUMB);
        Glide.with(getApplicationContext())
                .load(url)
                .centerCrop()
                .into(image_season_large);
        hideLoading();


    }
}
