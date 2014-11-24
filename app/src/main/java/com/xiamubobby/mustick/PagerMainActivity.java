package com.xiamubobby.mustick;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Administrator on 2014/11/24.
 */
public class PagerMainActivity extends Activity {
    private ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_main);

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
                return MyTestFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
