package remote.services;

import model.Show;
import remote.constants.ApiConfiguration;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by movile on 27/06/15.
 */
public interface ShowRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}?extended=full,images")
    void getShowInformation(
            @Path("show") String show,
            Callback<Show> callback);
}
