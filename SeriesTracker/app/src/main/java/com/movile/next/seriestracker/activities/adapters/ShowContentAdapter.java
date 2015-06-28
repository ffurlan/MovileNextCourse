package com.movile.next.seriestracker.activities.adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.next.seriestracker.activities.fragments.ShowDetailSeasonsFragment;
import com.movile.next.seriestracker.activities.fragments.ShowDetailsInfoFragment;
import com.movile.next.seriestracker.activities.model.Show;

/**
 * Created by movile on 21/06/15.
 */
public class ShowContentAdapter  extends FragmentPagerAdapter {

    private static final int POSITION_OVERVIEW = 0;
    private static final int POSITION_SEASONS = 1;

    private Show mShow;

    public ShowContentAdapter(FragmentManager fragmentManager, Show show)
    {
        super(fragmentManager);
        mShow = show;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        Bundle arguments = new Bundle();

        if (position == POSITION_OVERVIEW)
        {
            //arguments.putSerializable();
            fragment = new ShowDetailsInfoFragment(mShow);
        }

        if (position == POSITION_SEASONS) {
            //arguments.putSerializable();
            fragment = new ShowDetailSeasonsFragment(mShow);
        }
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == POSITION_OVERVIEW)
        {
            return "INFO";
        }

        if (position == POSITION_SEASONS) {
            return "SEASONS";
        }
        return "INFO";
    }

}
