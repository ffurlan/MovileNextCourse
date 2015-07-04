package com.movile.next.seriestracker.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.base.BaseNavigationToolbarActivity;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.List;

import com.movile.next.seriestracker.activities.adapters.EpisodeListAdapter;
import com.movile.next.seriestracker.activities.listener.OnEpisodeClick;
import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.Images;
import com.movile.next.seriestracker.activities.model.Season;
import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.presenter.SeasonDetailsHeaderPresenter;
import com.movile.next.seriestracker.activities.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.activities.view.SeasonDetailsHeaderView;
import com.movile.next.seriestracker.activities.view.SeasonDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnEpisodeClick, SeasonDetailsHeaderView {

    private static SeasonDetailsPresenter seasonDetailsPresenter;
    private static SeasonDetailsHeaderPresenter seasonDetailsHeaderPresenter;
    private static EpisodeListAdapter mAdapter;
    private static View mHeaderView;

    public static String EXTRA_SEASONNUMBER = "SEASON_NUMBER";
    private static int mSeasonNumber = 1;
    public static String EXTRA_SHOWNAME = "SHOW_NAME";
    private static String mShowName = "house";
    public static String EXTRA_SHOW = "SHOW";
    private static Show mShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        showLoading();
        configureToolbar();

        GetExtrasFromBundle();


        ListView view = (ListView) findViewById(R.id.list_view);
        mHeaderView = getLayoutInflater().inflate(R.layout.season_details_header, null);
        view.addHeaderView(mHeaderView);
        mAdapter = new EpisodeListAdapter(this, this);
        view.setAdapter(mAdapter);


        //region MVP
        seasonDetailsPresenter = new SeasonDetailsPresenter(this, getString(R.string.api_url_base));
        seasonDetailsPresenter.getSeasonEpisodeList(mShowName, mSeasonNumber);

        seasonDetailsHeaderPresenter = new SeasonDetailsHeaderPresenter(this, getString(R.string.api_url_base));
        seasonDetailsHeaderPresenter.getSeasonDetails(mShowName, mSeasonNumber);


        getSupportActionBar().setTitle(MessageFormat.format("Season{0}", mSeasonNumber));
    }

    @Override
    public void displayEpisodeList(List<Episode> episodes) {
        mAdapter.UpdateViewData(episodes);
    }

    @Override
    public void displaySeasonInfo(List<Season> seasons) {

    }

    @Override
    public void onEpisodeClick(Episode episode) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODENUMBER, episode.number());
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SEASONNUMBER, episode.season());
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SERIESNAME, mShowName);
        startActivity(intent);
    }

    @Override
    public void displaySeasonHeader(Season season) {
        TextView txtSeasonRatting = (TextView) findViewById(R.id.text_seasonRating);
        DecimalFormat df = new DecimalFormat("#.#");
        String rating = df.format(season.rating());
        txtSeasonRatting.setText(rating);

        ImageView image_season_small = (ImageView) findViewById(R.id.image_season);
        String urlImgSmall = season.images().poster().get(Images.ImageSize.FULL);
        Glide.with(getApplicationContext())
                .load(urlImgSmall)
                .centerCrop()
                .into(image_season_small);

        ImageView image_season_large = (ImageView) findViewById(R.id.image_seasonTitle);
        String urlImgTitle =  mShow.images().thumb().get(Images.ImageSize.FULL);
        Glide.with(getApplicationContext())
                .load(urlImgTitle)
                .centerCrop()
                .into(image_season_large);

        hideLoading();


    }

    public void GetExtrasFromBundle()
    {
        Intent intent = getIntent();
        mSeasonNumber = (int)intent.getExtras().getLong(EXTRA_SEASONNUMBER);
        mShowName = intent.getExtras().getString(EXTRA_SHOWNAME);
        mShow = (Show)intent.getExtras().getSerializable(EXTRA_SHOW);
    }

}
