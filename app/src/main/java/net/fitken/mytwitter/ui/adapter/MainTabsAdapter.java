package net.fitken.mytwitter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.fitken.mytwitter.ui.fragment.HomeFragment;
import net.fitken.mytwitter.ui.fragment.MentionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 3/11/2017.
 */

public class MainTabsAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private String tabTitles[] = new String[]{"Home", "Mentions"};

    public MainTabsAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(MentionFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
