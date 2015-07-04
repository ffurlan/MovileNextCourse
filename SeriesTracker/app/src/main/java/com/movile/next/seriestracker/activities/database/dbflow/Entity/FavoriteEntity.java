package com.movile.next.seriestracker.activities.database.dbflow.Entity;

import android.provider.BaseColumns;

import com.movile.next.seriestracker.activities.database.dbflow.SeriesTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntity extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public FavoriteEntity()
    {

    }

    public FavoriteEntity(String _slug, String _title)
    {
        slug = _slug;
        title = _title;
    }

    public String Title() { return title;}
    public String Slug() { return slug;}


}