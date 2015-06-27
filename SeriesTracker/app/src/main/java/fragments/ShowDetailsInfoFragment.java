package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;

import model.Show;
import presenter.ShowDetailsPresenter;
import remote.client.ShowRemoteCaller;
import view.ShowDetailsView;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsInfoFragment extends Fragment {

    private Show mShow;

    public ShowDetailsInfoFragment(Show show) {
        mShow = show;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_info_fragment, container, false);
        TextView txtShowSummary = (TextView) view.findViewById(R.id.text_show_summary);
        txtShowSummary.setText(mShow.overview());

        TextView txtBroadCasting = (TextView) view.findViewById(R.id.text_show_details_BroadCasting);
        txtBroadCasting.setText(mShow.network());

        TextView txtStatus = (TextView) view.findViewById(R.id.text_show_details_Status);
        txtStatus.setText(mShow.status());

        TextView txtSeasons = (TextView) view.findViewById(R.id.text_show_details_Seasons);
        txtSeasons.setText(mShow.overview());

        TextView txtStartedIn = (TextView) view.findViewById(R.id.text_show_details_Started);
        txtStartedIn.setText(mShow.firstAired());

        TextView txtCountry = (TextView) view.findViewById(R.id.text_show_details_Country);
        txtCountry.setText(mShow.country());

        TextView txtHomePage = (TextView) view.findViewById(R.id.text_show_details_HomePage);
        txtHomePage.setText(mShow.network());

        return view;
    }
}
