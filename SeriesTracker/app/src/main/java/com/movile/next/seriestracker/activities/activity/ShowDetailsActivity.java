package com.movile.next.seriestracker.activities.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.base.BaseNavigationToolbarActivity;

import com.movile.next.seriestracker.activities.adapters.ShowContentAdapter;
import com.movile.next.seriestracker.activities.callbacks.favoriteLoaderCallback;
import com.movile.next.seriestracker.activities.listener.OnFavoriteListener;
import com.movile.next.seriestracker.activities.model.Favorite;
import com.movile.next.seriestracker.activities.model.Images;
import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.activities.view.ShowDetailsView;

import java.text.DecimalFormat;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView, OnFavoriteListener {

    private static ShowDetailsPresenter showDetailsPresenter;
    public static String EXTRA_SHOWNAME = "SHOW_NAME";
    private static String mShowName = "house";
    public static String EXTRA_SHOW_FULL_NAME = "SHOW_FULL_NAME";
    private static String mShowFullName = "House";
    private static FloatingActionButton favoriteView;


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


        TextView txtShowRate = (TextView) findViewById(R.id.txtShowRate);

        DecimalFormat df = new DecimalFormat("#.#");
        String rating = df.format(show.rating());

        txtShowRate.setText(rating);

        ImageView imgShow = (ImageView) findViewById(R.id.imgHeaderShowDetails);
        String url = show.images().thumb().get(Images.ImageSize.FULL);
        Glide.with(getApplicationContext())
                .load(url)
                .centerCrop()
                .into(imgShow);


        ConfigureFavoriteButton(show);

        getLoaderManager().initLoader(
                0, null, new favoriteLoaderCallback(this, this, show.ids().slug(), show.title(), true)
        ).forceLoad();

        ViewPager viewPager = (ViewPager) findViewById(R.id.show_details_content);
        viewPager.setAdapter(new ShowContentAdapter(getSupportFragmentManager(), show));
    }

    public void GetExtrasFromBundle()
    {
        Intent intent = getIntent();
        mShowName = intent.getExtras().getString(EXTRA_SHOWNAME);
        mShowFullName = intent.getExtras().getString(EXTRA_SHOW_FULL_NAME);
    }

    @Override
    public void onFavoriteLoaded(Favorite favorite) {

        if (favorite != null) {
            favoriteView.setImageResource(R.drawable.show_details_favorite_on);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        }
        else {
            favoriteView.setImageResource(R.drawable.show_details_favorite_off);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_third));
        }
    }

    private void ConfigureFavoriteButton(Show show)
    {
        favoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite);
        final String slug = show.ids().slug();
        final Context context = this;
        final OnFavoriteListener listener = this;
        final String title = show.title();

        favoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().initLoader(
                        1, null, new favoriteLoaderCallback(listener, context, slug, title, false)
                ).forceLoad();
            }
        });


    }
}
