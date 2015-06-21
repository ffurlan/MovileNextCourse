package remote.services;

import java.util.List;

import model.Episode;
import remote.constants.ApiConfiguration;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by movile on 21/06/15.
 */
public interface SeasonEpisodesRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}?extended=full,images")
    void getSeasonEpisodes(
            @Path("show") String show,
            @Path("season") Integer season,
            Callback<List<Episode>> callback);
}
