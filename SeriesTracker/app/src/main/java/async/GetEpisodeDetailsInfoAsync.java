package async;

import android.content.Context;
import android.os.AsyncTask;

import business.FetchLocalEpisodeDetails;
import listener.OnEpisodeListener;
import model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class GetEpisodeDetailsInfoAsync extends AsyncTask<Void, Void, Episode> {

    private OnEpisodeListener mListener;
    private Context mContext;

    public GetEpisodeDetailsInfoAsync(OnEpisodeListener listener, Context context)
    {
        mListener = listener;
        mContext = context;
    }

    @Override
    protected Episode doInBackground(Void... params) {
        Episode episode = new FetchLocalEpisodeDetails().get(mContext);
        return episode;
    }

    @Override
    protected void onPostExecute(Episode episode) {
        mListener.onEpisodeLoaded(episode);
    }
}
