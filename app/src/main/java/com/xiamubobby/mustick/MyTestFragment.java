package com.xiamubobby.mustick;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2014/11/24.
 */
public class MyTestFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    private static final int POSITION_CONFIG = 0;
    private static final int POSITION_PREVIEW = 1;


    public static MyTestFragment newInstance(int pos) {
        MyTestFragment f = new MyTestFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, pos);
        f.setArguments(b);
        return f;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (position == POSITION_CONFIG) {
            return inflater.inflate(R.layout.poster_config, null);
        }
        else if (position == POSITION_PREVIEW) {
            return inflater.inflate(R.layout.test_fragment, null);
        }
        else {
            return inflater.inflate(R.layout.test_fragment, null);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }
}
