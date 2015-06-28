package com.movile.next.seriestracker.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;

import java.util.List;

import com.movile.next.seriestracker.activities.listener.OnShowClick;
import com.movile.next.seriestracker.activities.model.Images;
import com.movile.next.seriestracker.activities.model.Show;

/**
 * Created by movile on 27/06/15.
 */
public class ShowGridAdapter extends ArrayAdapter<Show> {

    private List<Show> mShows;
    private OnShowClick mClickListener = null;
    private Context mContext;

    public ShowGridAdapter(Context context, OnShowClick clickListener) {
        super(context, R.layout.shows_grid_item);
        mClickListener = clickListener;
        mContext = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shows_grid_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position, type);
        return view;
    }

    @Override
    public int getCount() {
        return mShows != null ? mShows.size() : 0;
    }

    @Override
    public Show getItem(int position) {
        return mShows.get(position);
    }


    public void UpdateViewData(List<Show> shows) {
        mShows = shows;
        notifyDataSetChanged();
    }

    private void populateViewFromHolder(ViewHolder holder, int position, int type) {
        final Show show = getItem(position);
        String url = show.images().poster().get(Images.ImageSize.FULL);
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(holder.GetShowImage());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onShowClick(show);
            }
        });
    }

    public static class ViewHolder {
        private View mRoot;
        private ImageView mShowImage;

        public ViewHolder(View root) {
            mShowImage = (ImageView) root.findViewById(R.id.showImage);
            mRoot = root;
        }

        public ImageView GetShowImage() {
            return mShowImage;
        }

        public View root() {
            return mRoot;
        }
    }
}