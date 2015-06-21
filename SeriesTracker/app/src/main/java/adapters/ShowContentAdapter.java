package adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.ShowDetailSeasonsFragment;
import fragments.ShowDetailsInfoFragment;
import model.Show;

/**
 * Created by movile on 21/06/15.
 */
public class ShowContentAdapter  extends FragmentPagerAdapter {

    private static final int POSITION_OVERVIEW = 0;
    private static final int POSITION_SEASONS = 1;

    private Show mShow;

    public ShowContentAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        //mShow = show;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        Bundle arguments = new Bundle();

        if (position == POSITION_OVERVIEW)
        {
            //arguments.putSerializable();
            fragment = new ShowDetailsInfoFragment();
        }

        if (position == POSITION_SEASONS) {
            //arguments.putSerializable();
            fragment = new ShowDetailSeasonsFragment();
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
