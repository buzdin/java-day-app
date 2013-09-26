package lv.jug.javaday.androidapp.presentation.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SchedulePageAdapter extends FragmentPagerAdapter {

    private List<RoomScheduleFragment> fragments;

    public SchedulePageAdapter(FragmentManager fm, List<RoomScheduleFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return fragments.get(i).getTitle();

    }
}