package remote.client;

import java.util.List;

import model.Show;
import remote.callbacks.ShowCallback;
import remote.callbacks.ShowDetailsCallback;
import remote.services.ShowRemoteService;
import remote.services.ShowsRemoteService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 27/06/15.
 */
public class ShowsRemoteCaller {
    private ShowCallback mCallback;
    private RestAdapter mAdapter;

    public ShowsRemoteCaller(ShowCallback callBack, String endpoint) {
        mCallback = callBack;
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public void getShows() {

        ShowsRemoteService service = mAdapter.create(ShowsRemoteService.class);

        service.getShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                mCallback.onShowsLoaded(shows);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
