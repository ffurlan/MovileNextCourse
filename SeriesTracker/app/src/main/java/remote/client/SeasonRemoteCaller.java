package remote.client;

import java.util.List;

import model.Season;
import remote.callbacks.SeasonDetailsHeaderCallback;
import remote.services.SeasonRemoteService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 21/06/15.
 */
public class SeasonRemoteCaller {
    private SeasonDetailsHeaderCallback mCallback;
    private RestAdapter mAdapter;
    private int mSeasonNumber;

    public SeasonRemoteCaller(SeasonDetailsHeaderCallback callBack, String endpoint, int seasonNumber)
    {
        mCallback = callBack;
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mSeasonNumber = seasonNumber;
    }

    public void getSeasons(String show){

        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mCallback.onSeasonLoaded(seasons.get(mSeasonNumber));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
