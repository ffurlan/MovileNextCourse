package com.movile.next.seriestracker.activities.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.next.seriestracker.activities.database.dbflow.DAO.FavoriteDAOFlow;
import com.movile.next.seriestracker.activities.database.manual.DAO.FavoriteDAO;
import com.movile.next.seriestracker.activities.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteLoader extends AsyncTaskLoader<Favorite> {
    private Context mContext;
    private String mSlug;
    private String mTitle;
    public Boolean mReadOnly;

    public FavoriteLoader(Context context, String slug, String title, Boolean readOnly) {
        super(context);
        mContext = context;
        mSlug = slug;
        mTitle = title;
        mReadOnly = readOnly;
    }

    @Override
    public Favorite loadInBackground() {
        if (mReadOnly)
            return new FavoriteDAOFlow().query(mSlug);
            //return new FavoriteDAO(mContext).query(mSlug);

        //Favorite favorite = new FavoriteDAO(mContext).query(mSlug);
        Favorite favorite = new FavoriteDAOFlow().query(mSlug);
        if (favorite == null)
            SaveFavorite();
        else
            DeleteFavorite();
        return new FavoriteDAOFlow().query(mSlug);
    }

    public void SaveFavorite() {
        Favorite favorite = new Favorite(mSlug, mTitle);
        //new FavoriteDAO(mContext).save(favorite);
        new FavoriteDAOFlow().save(favorite);
    }

    public void DeleteFavorite() {
        //new FavoriteDAO(mContext).delete(mSlug);
        new FavoriteDAOFlow().delete(mSlug);
    }


}