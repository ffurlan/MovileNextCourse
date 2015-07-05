package com.movile.next.seriestracker.activities.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.listener.OnFavoriteClick;
import com.movile.next.seriestracker.activities.listener.OnFavoriteListener;
import com.movile.next.seriestracker.activities.model.Favorite;

import java.util.List;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesAdapter extends CursorAdapter {

    private OnFavoriteClick mClickListener;

    public FavoritesAdapter(Context context, OnFavoriteClick clickListener)
    {
        super(context, null);
        mClickListener = clickListener;
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_shows_fragment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        final String slug = cursor.getString(cursor.getColumnIndexOrThrow("slug"));
        final String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.GetShowTitle().setText(title);
        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onFavoriteClick(slug, title);
            }
        });

    }

    public void UpdateViewData(Cursor cursor)
    {
        swapCursor(cursor);
    }

    public static class ViewHolder {
        private View mRoot;
        private TextView showTitle;
        public ViewHolder(View root) {
            showTitle = (TextView)root.findViewById(R.id.favorite_show_title);
            mRoot = root;
        }
        public TextView GetShowTitle() {
            return showTitle;
        }

        public View root() { return mRoot; }

    }
}
