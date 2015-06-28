package com.movile.next.seriestracker.activities.model.converter;

import com.google.gson.Gson;
import com.movile.next.seriestracker.activities.model.Episode;
import com.movile.next.seriestracker.activities.model.Season;
import com.movile.next.seriestracker.activities.model.Show;

import java.io.Reader;

public class ModelConverter {

    private Gson mGson;

    public ModelConverter() {
        mGson = new Gson();
    }

    public Episode toEpisode(Reader reader) {
        return mGson.fromJson(reader, Episode.class);
    }

    public Show toShow(Reader reader) {
        return null;
    }

    public Season toSeason(Reader reader) {
        return null;
    }

}
