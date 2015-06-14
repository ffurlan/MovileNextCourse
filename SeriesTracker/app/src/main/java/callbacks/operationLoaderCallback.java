package callbacks;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import listener.OnEpisodeListener;
import loader.EpisodeDetailsLoader;
import model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class operationLoaderCallback implements LoaderManager.LoaderCallbacks<Episode>{

    private OnEpisodeListener mListener;
    private Context mContext;
    private String mUrl;

    public operationLoaderCallback(OnEpisodeListener listener, Context context, String url)
    {
        mListener = listener;
        mContext = context;
        mUrl = url;
    }

    @Override
    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        return new EpisodeDetailsLoader(mContext, mUrl);
    }

    @Override
    public void onLoadFinished(Loader<Episode> loader, Episode data) {
        mListener.onEpisodeLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
