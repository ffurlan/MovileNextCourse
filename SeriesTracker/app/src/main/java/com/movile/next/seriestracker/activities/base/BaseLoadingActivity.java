package com.movile.next.seriestracker.activities.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.movile.next.seriestracker.R;

/**
 * Created by movile on 21/06/15.
 */
public class BaseLoadingActivity extends AppCompatActivity {

    public void showLoading() {
        findViewById(R.id.base_loading_container).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.base_loading_container).setVisibility(View.GONE);
    }

}
