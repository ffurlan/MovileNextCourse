package com.movile.next.seriestracker.activities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by movile on 28/06/15.
 */
public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder>{
    private Context mContext;
    private String[] mGenres;
    private int mLayout;

    public GenresAdapter(Context context, int layout) {
        mContext = context;
        mLayout = layout;
    }

    public void updateGenres(String[] genres) {
        mGenres = genres;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String genre = mGenres[position];
        holder.SeasonGenre().setText(genre);
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.length : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mGenre;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mGenre = (TextView) mRoot.findViewById(R.id.text_seasonGenres);
        }

        public View root() {
            return mRoot;
        }

        public TextView SeasonGenre() {
            return mGenre;
        }
    }
}
