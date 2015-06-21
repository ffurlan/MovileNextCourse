package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailSeasonsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_seasons_fragment, container, false);
        return view;
    }



}
