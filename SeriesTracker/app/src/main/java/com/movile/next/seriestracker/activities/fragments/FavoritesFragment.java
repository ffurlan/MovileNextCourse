package com.movile.next.seriestracker.activities.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activities.activity.ShowDetailsActivity;
import com.movile.next.seriestracker.activities.adapters.FavoritesAdapter;
import com.movile.next.seriestracker.activities.listener.OnFavoriteClick;
import com.movile.next.seriestracker.activities.presenter.FavoritesPresenter;
import com.movile.next.seriestracker.activities.view.FavoritesView;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesFragment extends Fragment implements FavoritesView, OnFavoriteClick {

    private static FavoritesAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_shows_fragment, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FavoritesPresenter favoritesPresenter = new FavoritesPresenter(this, this.getView().getContext());
        favoritesPresenter.getFavorites(getLoaderManager());
    }

    @Override
    public void displayFavorites(Cursor cursor) {
        ListView view = (ListView) this.getView().findViewById(R.id.list_view_favorites);
        mAdapter = new FavoritesAdapter(this.getView().getContext(), this);
        view.setAdapter(mAdapter);
        ImageView imageHeader = (ImageView) this.getView().findViewById(R.id.image_favorites_header);
        TextView txtEmpty = (TextView) this.getView().findViewById(R.id.text_favorites_empty);
        if (cursor.getCount() == 0)
        {
            txtEmpty.setVisibility(View.VISIBLE);
            imageHeader.setImageResource(R.drawable.favorites_header_tv_unhappy);
        }
        else {
            txtEmpty.setVisibility(View.GONE);
            imageHeader.setImageResource(R.drawable.favorites_header_tv_happy);
            mAdapter.UpdateViewData(cursor);
        }
    }

    @Override
    public void onFavoriteClick(String slug, String title) {
        Intent intent = new Intent(this.getActivity().getApplicationContext(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOWNAME, slug);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW_FULL_NAME, title);
        startActivity(intent);
    }
}
