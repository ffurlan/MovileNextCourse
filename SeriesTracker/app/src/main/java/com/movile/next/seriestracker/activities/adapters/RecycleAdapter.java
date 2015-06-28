package com.movile.next.seriestracker.activities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;

import java.text.MessageFormat;
import java.util.List;

import com.movile.next.seriestracker.activities.listener.OnSeasonClick;
import com.movile.next.seriestracker.activities.model.Images;
import com.movile.next.seriestracker.activities.model.Season;

/**
 * Created by movile on 27/06/15.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<Season> mSeasons;
    private int mLayout;
    private OnSeasonClick mSeasonClick;

    public RecycleAdapter(Context context, int layout, OnSeasonClick seasonClick) {
        mContext = context;
        mLayout = layout;
        mSeasonClick = seasonClick;
    }

    public void updateSeasons(List<Season> seasons) {
        mSeasons = seasons;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Season season = mSeasons.get(position);

        holder.SeasonNumber().setText(MessageFormat.format("Season {0}", season.number().toString()));
        holder.SeasonEpisodesCount().setText(MessageFormat.format("{0} episodes", season.episodeCount().toString()));
        String url = season.images().poster().get(Images.ImageSize.THUMB);
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.overlay)
                .centerCrop()
                .into(holder.SeasonImage());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSeasonClick != null)
                    mSeasonClick.onSeasonClick(season);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mSeasonNumber;
        private TextView mSeasonEpisodesCount;
        private ImageView mSeasonImage;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mSeasonNumber = (TextView) mRoot.findViewById(R.id.seasonNumber);
            mSeasonEpisodesCount = (TextView) mRoot.findViewById(R.id.seasonCount);
            mSeasonImage = (ImageView) mRoot.findViewById(R.id.image_season);
        }

        public View root() {
            return mRoot;
        }

        public TextView SeasonNumber() {
            return mSeasonNumber;
        }

        public TextView SeasonEpisodesCount() {
            return mSeasonEpisodesCount;
        }

        public ImageView SeasonImage() { return mSeasonImage;}

    }
}
