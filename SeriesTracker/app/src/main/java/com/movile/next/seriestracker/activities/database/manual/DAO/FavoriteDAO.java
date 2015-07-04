package com.movile.next.seriestracker.activities.database.manual.DAO;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.movile.next.seriestracker.activities.database.manual.Entity.FavoriteEntity;
import com.movile.next.seriestracker.activities.database.manual.helper.ProviderUriHelper;
import com.movile.next.seriestracker.activities.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDAO {

    private Context mContext;

    public FavoriteDAO(Context context) {
        mContext = context;
    }

    public void save(Favorite favorite) {
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);

        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        mContext.getContentResolver().insert(uri, entity.toContentValues());
    }

    public Cursor all() {
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
        return mContext.getContentResolver().query(uri, null, null, null, FavoriteEntity.FavoriteEntityFields.COLUMN_TITLE);
    }

    public Favorite query(String slug) {
        Cursor cursor = null;
        Favorite favorite = null;

        try {
            Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
            cursor = mContext.getContentResolver().query(uri, null, FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{slug}, null);

            if (cursor.moveToFirst()) {
                FavoriteEntity entity = new FavoriteEntity().fromCursor(cursor);
                favorite = new Favorite(entity.slug(), entity.title());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return favorite;
    }

    public void delete(String slug) {
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
        mContext.getContentResolver().delete(uri, FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{slug});
    }
}

