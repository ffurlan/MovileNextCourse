package com.movile.next.seriestracker;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import com.movile.next.seriestracker.base.BaseNavigationToolbarActivity;

import java.text.MessageFormat;

import adapters.EpisodeListAdapter;
import adapters.ShowContentAdapter;
import presenter.SeasonDetailsHeaderPresenter;
import presenter.SeasonDetailsPresenter;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details);
        configureToolbar();

        ViewPager viewPager = (ViewPager) findViewById(R.id.show_details_content);
        viewPager.setAdapter(new ShowContentAdapter(getSupportFragmentManager()));




        getSupportActionBar().setTitle("House");
    }
}
