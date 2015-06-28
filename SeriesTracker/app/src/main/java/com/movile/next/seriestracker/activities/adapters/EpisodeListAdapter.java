package com.movile.next.seriestracker.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.next.seriestracker.R;

import java.util.List;

import com.movile.next.seriestracker.activities.listener.OnEpisodeClick;
import com.movile.next.seriestracker.activities.model.Episode;

/**
 * Created by movile on 21/06/15.
 */
public class EpisodeListAdapter extends ArrayAdapter<Episode> {


    private List<Episode> mEpisodes;
    private OnEpisodeClick mClickListener = null;

    public EpisodeListAdapter(Context context, OnEpisodeClick clickListener) {
        super(context, R.layout.season_details_item);
        mClickListener = clickListener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.season_details_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position, type);
        return view;
    }

    @Override
    public Episode getItem(int position)
    {
        return mEpisodes.get(position);
    }

    @Override
    public int getCount() {
        return mEpisodes != null ? mEpisodes.size() : 0;
    }

    public void UpdateViewData(List<Episode> episodes)
    {
        mEpisodes = episodes;
        notifyDataSetChanged();
    }

    private void populateViewFromHolder(ViewHolder holder, int position, int type) {
        final Episode episode = getItem(position);
        holder.GetEpisodeNumber().setText(episode.number().toString());
        holder.GetEpisodeTitle().setText(episode.title());
        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onEpisodeClick(episode);
            }
        });
    }

    public static class ViewHolder {
        private View mRoot;
        private TextView episodeNumber;
        private TextView episodeTitle;
        public ViewHolder(View root) {
            episodeNumber = (TextView)root.findViewById(R.id.season_details_episodeNumber);
            episodeTitle = (TextView)root.findViewById(R.id.season_details_episodeTitle);
            mRoot = root;
        }
        public TextView GetEpisodeNumber() {
            return episodeNumber;
        }

        public TextView GetEpisodeTitle() {
            return episodeTitle;
        }

        public View root() { return mRoot; }
    }
}


