package com.movile.next.seriestracker.activities.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.base.BaseNavigationToolbarActivity;

import java.util.List;

import com.movile.next.seriestracker.activities.adapters.ShowGridAdapter;
import com.movile.next.seriestracker.activities.listener.OnShowClick;
import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.presenter.ShowPresenter;
import com.movile.next.seriestracker.activities.services.UpdateService;
import com.movile.next.seriestracker.activities.view.ShowsView;

/**
 * Created by movile on 27/06/15.
 */
public class ShowActivity  extends Activity implements ShowsView, OnShowClick {

    private static ShowGridAdapter mAdapter;
    private static ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_activity);

        showPresenter = new ShowPresenter(this, getString(R.string.api_url_base));
        showPresenter.getShows();

        GridView gridview = (GridView) findViewById(R.id.gridShows);
        mAdapter = new ShowGridAdapter(this, this);
        gridview.setAdapter(mAdapter);

        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, new Intent(getApplicationContext(), UpdateService.class), 0);
        AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 3000, pendingIntent);

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
