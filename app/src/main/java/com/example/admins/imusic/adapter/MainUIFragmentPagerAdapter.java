package com.example.admins.imusic.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admins.imusic.MyApplication;

import java.util.List;

public class MainUIFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> frgList;
    private Context context;
    private String tabTitles[] = {"本地", "推荐", "MV"};
    public MainUIFragmentPagerAdapter(FragmentManager fm, List<Fragment> frgList) {
        super(fm);
        this.frgList=frgList;
        this.context= MyApplication.getContext();
    }

    @Override
    public Fragment getItem(int position) {
        return frgList.get(position);
    }

    @Override
    public int getCount() {
        return frgList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
