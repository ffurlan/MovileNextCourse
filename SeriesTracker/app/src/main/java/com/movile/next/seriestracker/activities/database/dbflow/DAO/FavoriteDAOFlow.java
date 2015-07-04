package com.movile.next.seriestracker.activities.database.dbflow.DAO;

import android.database.Cursor;

import com.movile.next.seriestracker.activities.database.dbflow.Entity.FavoriteEntity;
import com.movile.next.seriestracker.activities.database.dbflow.Entity.FavoriteEntity$Table;
import com.movile.next.seriestracker.activities.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDAOFlow {

    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        entity.save();
    }

    public Cursor all() {
        return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
    }

    public Favorite query(String slug) {
        FavoriteEntity favoriteEntity = new Select()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .querySingle();
        if (favoriteEntity == null)
            return null;
        return new Favorite(favoriteEntity.Slug(), favoriteEntity.Title());
    }

    public void delete(String slug) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .queryClose();
    }
}
