package com.movile.next.seriestracker.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.base.BaseNavigationToolbarActivity;

import java.text.MessageFormat;

import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.Images;
import com.movile.next.seriestracker.activities.presenter.EpisodeDetailsPresenter;
import com.movile.next.seriestracker.activities.util.FormatUtil;
import com.movile.next.seriestracker.activities.view.EpisodeDetailsView;


public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView {


    private static EpisodeDetailsPresenter episodeDetailsPresenter;

    public static String EXTRA_EPISODENUMBER = "EPISODE_NUMBER";
    public static String EXTRA_SEASONNUMBER = "SEASON_NUMBER";
    public static String EXTRA_SERIESNAME = "SERIES_NAME";

    private static int mEpisodeNumber = 1;
    private static int mSeasonNumber = 1;
    private static String mSeriesName = "house";


     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.episode_details_activity);
         configureToolbar();
         showLoading();


        //region MVP
        episodeDetailsPresenter = new EpisodeDetailsPresenter(this, getString(R.string.api_url_base));

        GetExtrasFromBundle();

        episodeDetailsPresenter.getEpisodeDetails(mSeriesName, mSeasonNumber, mEpisodeNumber);


        //endregion

        //region Com Task Assíncrona
        //GetEpisodeDetailsInfoAsync task = new GetEpisodeDetailsInfoAsync(this, this.getApplicationContext());
        //task.execute();]
        //endregion

        //region Com Loader

        //String url = getString(R.string.api_url_base) + getString(R.string.api_url_episode);
        //url = MessageFormat.format(url, "person-of-interest", 1, 2);
        //getLoaderManager().initLoader(
        //        0, null, new operationLoaderCallback(this, this, url)
        //).forceLoad();
        //endregion

        //region Com API
        //EpisodeRemoteCaller erc = new EpisodeRemoteCaller(this, getString(R.string.api_url_base));
        //erc.getEpisodeDetails("house", 6, 8);
        //endregion

    }

    public void GetExtrasFromBundle()
    {
        Intent intent = getIntent();
        mEpisodeNumber = (int)intent.getExtras().getLong(EXTRA_EPISODENUMBER);
        mSeasonNumber = (int)intent.getExtras().getLong(EXTRA_SEASONNUMBER);
        mSeriesName = intent.getExtras().getString(EXTRA_SERIESNAME);
    }


    @Override
    public void displayEpisode(Episode episode) {

        getSupportActionBar().setTitle(MessageFormat.format("S{0} - Episode {1}", episode.season(), episode.number()));

        TextView txtScreenshotTitle = (TextView) findViewById(R.id.episode_details_screenshot_Title);
        txtScreenshotTitle.setText(episode.title());

        TextView txtClockTitle = (TextView) findViewById(R.id.episode_details_clock_Title);
        FormatUtil fd = new FormatUtil();
        txtClockTitle.setText(fd.formatDate(fd.formatDate(episode.firstAired())));

        TextView txtSummaryDescription = (TextView) findViewById(R.id.episode_summary_Description);
        txtSummaryDescription.setText(episode.overview());

        //RemoteImageAsyncTask task = new RemoteImageAsyncTask(this);
        //task.execute(episode.images().screenshot().get(Images.ImageSize.THUMB));

        ImageView img = (ImageView) findViewById(R.id.episode_details_screenshot);
        String url = episode.images().screenshot().get(Images.ImageSize.FULL);
        Glide.with(getApplicationContext())
                .load(url)
                .centerCrop()
                .into(img);
        hideLoading();
    }

    //region Callbacks

     //public void onEpisodeLoaded(Episode episode) {
    //    TextView txtScreenshotTitle = (TextView) findViewById(R.id.episode_details_screenshot_Title);
    //    txtScreenshotTitle.setText(episode.title());

     //   TextView txtClockTitle = (TextView) findViewById(R.id.episode_details_clock_Title);
     //   FormatUtil fd = new FormatUtil();
      //  txtClockTitle.setText(fd.formatDate(fd.formatDate(episode.firstAired())));
//
      //  TextView txtSummaryDescription = (TextView) findViewById(R.id.episode_summary_Description);
      //  txtSummaryDescription.setText(episode.overview());

        //RemoteImageAsyncTask task = new RemoteImageAsyncTask(this);
        //task.execute(episode.images().screenshot().get(Images.ImageSize.THUMB));

      //  ImageView img = (ImageView) findViewById(R.id.episode_details_screenshot);
      //  String url = episode.images().screenshot().get(Images.ImageSize.THUMB);
      //  Glide.with(this)
       //         .load(url)
       //         .placeholder(R.drawable.overlay)
      //          .centerCrop()
      //          .into(img);

    //}

    //public void onLoadImage(Bitmap bitmap) {
        //ImageView img = (ImageView) findViewById(R.id.episode_details_screenshot);
        //img.setImageBitmap(bitmap);
    //}
    //endregion

    //region Save Instance X Restore Instance
    //Override
    //public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //final TextView text = (TextView)findViewById(R.id.episode_details_screenshot_Title);
        //String videoTitle = text.toString();
       // outState.putString("detailTitle", "The Following - Season 2 - Episode 4");
        //super.onSaveInstanceState(outState, outPersistentState);
        //Log.d(EpisodeDetailsActivity.class.getName(), "onSaveInstanceState");
    //}


    //Override
   // protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        //TextView txtView = (TextView) findViewById(R.id.episode_details_screenshot_Title);
       // txtView.setText(savedInstanceState.getString("detailTitle"));
        //Log.d(EpisodeDetailsActivity.class.getName(), "onRestoreInstanceState");
    //}
    //endregion

    //region Ciclo de Vida

    //Override
    //protected void onResume() {
    //    super.onResume();
        //Log.d(EpisodeDetailsActivity.class.getName(), "onResume");
    //}

    //Override
    //protected void onStart() {
    //    super.onStart();
        //Log.d(EpisodeDetailsActivity.class.getName(), "onStart");
    //}

    //Override
    //protected void onPause() {
      //  super.onPause();
        //Log.d(EpisodeDetailsActivity.class.getName(), "onPause");
    //}

    //Override
    //protected void onStop() {
    //    super.onStop();
        //Log.d(EpisodeDetailsActivity.class.getName(), "onStop");
   // }

    //Override
    //protected void onRestart() {
        //Log.d(EpisodeDetailsActivity.class.getName(), "onRestart");
    //    super.onRestart();
    //}

    //Override
    //protected void onDestroy() {

        //Log.d(EpisodeDetailsActivity.class.getName(), "onDestroy");
      //  super.onDestroy();
    //}
    //endregion

    //Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.episode_details_menu, menu);
        //return true;
    //}

    //Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        //return super.onOptionsItemSelected(item);
    //}
}
