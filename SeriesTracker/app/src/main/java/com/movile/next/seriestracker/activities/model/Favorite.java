package com.movile.next.seriestracker.activities.model;

/**
 * Created by movile on 04/07/15.
 */
public class Favorite {

    private String mSlug;
    private String mTitle;

    public Favorite(String slug, String title) {
        mSlug = slug;
        mTitle = title;
    }

    public String slug() {
        return mSlug;
    }

    public String title() {
        return mTitle;
    }

}
