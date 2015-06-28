package com.movile.next.seriestracker.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.base.BaseNavigationToolbarActivity;

import com.movile.next.seriestracker.activities.adapters.ShowContentAdapter;
import com.movile.next.seriestracker.activities.model.Images;
import com.movile.next.seriestracker.activities.model.Show;
import com.movile.next.seriestracker.activities.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.activities.view.ShowDetailsView;

import java.text.DecimalFormat;

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

        TextView txtShowSummary = (TextView) findViewById(R.id.txtShowRate);

        DecimalFormat df = new DecimalFormat("#.#");
        String rating = df.format(show.rating());

        txtShowSummary.setText(rating);

        ImageView imgShow = (ImageView) findViewById(R.id.imgHeaderShowDetails);
        String url = show.images().poster().get(Images.ImageSize.THUMB);
        Glide.with(getApplicationContext())
                .load(url)
                .placeholder(R.drawable.overlay)
                .centerCrop()
                .into(imgShow);

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
