package com.maxm.just_stamped.tabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maxm.just_stamped.js.R;

/**
 * Created by Mr_M on 27.10.2016.
 */

public class TabCommon extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_common, container, false);
        return v;
    }
}
