package com.movile.next.seriestracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.base.BaseNavigationToolbarActivity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import adapters.EpisodeListAdapter;
import adapters.RecycleAdapter;
import adapters.ShowContentAdapter;
import listener.OnSeasonClick;
import model.Episode;
import model.Images;
import model.Season;
import model.Show;
import presenter.SeasonDetailsHeaderPresenter;
import presenter.SeasonDetailsPresenter;
import presenter.ShowDetailsPresenter;
import view.SeasonDetailsView;
import view.ShowDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView {

    private static ShowDetailsPresenter showDetailsPresenter;
    public static String EXTRA_SHOWNAME = "SHOW_NAME";
    private static String mShowName = "house";
    public static String EXTRA_SHOW_FULL_NAME = "SHOW_FULL_NAME";
    private static String mShowFullName = "House";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details);

        configureToolbar();

        GetExtrasFromBundle();

        showDetailsPresenter = new ShowDetailsPresenter(this, getString(R.string.api_url_base));
        showDetailsPresenter.getShowInformation(mShowName);


        getSupportActionBar().setTitle(mShowFullName);
    }

    @Override
    public void displayShowInformation(Show show) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.show_details_content);
        viewPager.setAdapter(new ShowContentAdapter(getSupportFragmentManager(), show));
    }

    public void GetExtrasFromBundle()
    {
        Intent intent = getIntent();
        mShowName = intent.getExtras().getString(EXTRA_SHOWNAME);
        mShowFullName = intent.getExtras().getString(EXTRA_SHOW_FULL_NAME);
    }

}
