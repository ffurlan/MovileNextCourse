package com.movile.next.seriestracker.activities.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.next.seriestracker.activities.business.FetchRemoteEpisodeDetails;
import com.movile.next.seriestracker.activities.model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class EpisodeDetailsLoader extends AsyncTaskLoader<Episode> {

    private Context mContext;
    private String mUrl;

    public EpisodeDetailsLoader(Context context, String url)
    {
        super(context);
        mContext = context;
        mUrl = url;
    }

    @Override
    public Episode loadInBackground() {
        return new FetchRemoteEpisodeDetails().Get(mContext, mUrl);
    }



}
