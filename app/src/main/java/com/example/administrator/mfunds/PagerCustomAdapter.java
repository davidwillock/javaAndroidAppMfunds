package com.example.administrator.mfunds;

/**
 * Created by Administrator on 17/07/2017.
 */

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class PagerCustomAdapter extends FragmentPagerAdapter{

    List<Fragment> fragmentList;

    public PagerCustomAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] names = new String[]{"PerformanceStats", "Assets", "Sector"};
        return names[position];
    }


}
