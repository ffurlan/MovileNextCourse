package remote.client;

import model.Show;
import remote.callbacks.ShowDetailsCallback;
import remote.services.ShowRemoteService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 27/06/15.
 */
public class ShowRemoteCaller {
    private ShowDetailsCallback mCallback;
    private RestAdapter mAdapter;

    public ShowRemoteCaller(ShowDetailsCallback callBack, String endpoint) {
        mCallback = callBack;
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public void getShowInformation(String show) {

        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getShowInformation(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mCallback.onShowLoaded(show);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
   }
}
