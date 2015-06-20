package com.movile.next.seriestracker;

import android.graphics.Bitmap;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import listener.OnEpisodeImageListener;
import listener.OnEpisodeListener;
import model.Episode;
import model.Images;
import presenter.EpisodeDetailsPresenter;
import remote.server.EpisodeRemoteCaller;
import remote.callbacks.EpisodeDetailsCallback;
import util.FormatUtil;
import view.EpisodeDetailsView;


public class EpisodeDetailsActivity extends ActionBarActivity implements EpisodeDetailsView {


    private static EpisodeDetailsPresenter episodeDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        //region MVP
        if (episodeDetailsPresenter == null)
            episodeDetailsPresenter = new EpisodeDetailsPresenter(this, getString(R.string.api_url_base));

        episodeDetailsPresenter.getEpisodeDetails("house", 2, 8);


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

    @Override
    public void displayEpisode(Episode episode) {
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
        String url = episode.images().screenshot().get(Images.ImageSize.THUMB);
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.overlay)
                .centerCrop()
                .into(img);
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
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //final TextView text = (TextView)findViewById(R.id.episode_details_screenshot_Title);
        //String videoTitle = text.toString();
        outState.putString("detailTitle", "The Following - Season 2 - Episode 4");
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(EpisodeDetailsActivity.class.getName(), "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView txtView = (TextView) findViewById(R.id.episode_details_screenshot_Title);
        txtView.setText(savedInstanceState.getString("detailTitle"));
        Log.d(EpisodeDetailsActivity.class.getName(), "onRestoreInstanceState");
    }
    //endregion

    //region Ciclo de Vida

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(EpisodeDetailsActivity.class.getName(), "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(EpisodeDetailsActivity.class.getName(), "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(EpisodeDetailsActivity.class.getName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(EpisodeDetailsActivity.class.getName(), "onStop");
    }

    @Override
    protected void onRestart() {
        Log.d(EpisodeDetailsActivity.class.getName(), "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(EpisodeDetailsActivity.class.getName(), "onDestroy");
        super.onDestroy();
    }
    //endregion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.episode_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
