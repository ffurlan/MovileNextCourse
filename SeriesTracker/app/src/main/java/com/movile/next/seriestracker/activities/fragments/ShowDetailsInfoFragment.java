package com.movile.next.seriestracker.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;

import com.movile.next.seriestracker.activities.adapters.GenresAdapter;
import com.movile.next.seriestracker.activities.adapters.RecycleAdapter;
import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.util.FormatUtil;

import java.util.Date;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsInfoFragment extends Fragment {

    private Show mShow;
    private GenresAdapter mAdapter;

    public ShowDetailsInfoFragment(Show show) {
        mShow = show;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_info_fragment, container, false);
        TextView txtShowSummary = (TextView) view.findViewById(R.id.text_show_summary);
        txtShowSummary.setText(mShow.overview());

        TextView txtStatus = (TextView) view.findViewById(R.id.text_show_details_Status);
        txtStatus.setText(mShow.status());

        TextView txtStartedIn = (TextView) view.findViewById(R.id.text_show_details_Started);
        txtStartedIn.setText(mShow.year().toString());

        TextView txtCountry = (TextView) view.findViewById(R.id.text_show_details_Country);
        txtCountry.setText(mShow.country());

        TextView txtLanguage = (TextView) view.findViewById(R.id.text_show_details_Language);
        txtLanguage.setText(mShow.language());

        configureRecyclerView(view);

        return view;
    }

    private void configureRecyclerView(View view) {
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.genres_recycler);
        rView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new GenresAdapter(view.getContext(), R.layout.season_info_genres);
        rView.setAdapter(mAdapter);

        mAdapter.updateGenres(mShow.genres());
    }
}
