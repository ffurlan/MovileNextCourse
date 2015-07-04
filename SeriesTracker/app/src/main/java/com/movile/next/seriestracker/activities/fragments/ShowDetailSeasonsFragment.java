package com.movile.next.seriestracker.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.activity.SeasonDetailsActivity;

import java.util.List;

import com.movile.next.seriestracker.activities.adapters.RecycleAdapter;
import com.movile.next.seriestracker.activities.listener.OnSeasonClick;
import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.Season;
import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.activities.view.SeasonDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailSeasonsFragment extends Fragment implements SeasonDetailsView, OnSeasonClick {

    private RecycleAdapter mAdapter;
    private Show mShow;
    private static SeasonDetailsPresenter seasonDetailsPresenter;


    public ShowDetailSeasonsFragment(Show show) {
        mShow = show;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_seasons_fragment, container, false);
        configureRecyclerView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        seasonDetailsPresenter = new SeasonDetailsPresenter(this, getString(R.string.api_url_base));
        seasonDetailsPresenter.getSeasons(mShow.ids().slug());
    }

    private void configureRecyclerView(View view) {
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.seasons_recycler);
        rView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new RecycleAdapter(view.getContext(), R.layout.show_season_fragment_item, this);
        rView.setAdapter(mAdapter);
    }

    @Override
    public void displayEpisodeList(List<Episode> episode) {

    }

    @Override
    public void displaySeasonInfo(List<Season> seasons) {
        mAdapter.updateSeasons(seasons);
    }

    @Override
    public void onSeasonClick(Season season) {
        Intent intent = new Intent(this.getActivity(), SeasonDetailsActivity.class);
        intent.putExtra(SeasonDetailsActivity.EXTRA_SEASONNUMBER, season.number());
        intent.putExtra(SeasonDetailsActivity.EXTRA_SHOWNAME, mShow.ids().slug());
        intent.putExtra(SeasonDetailsActivity.EXTRA_SHOW, mShow);
        startActivity(intent);
    }
}
