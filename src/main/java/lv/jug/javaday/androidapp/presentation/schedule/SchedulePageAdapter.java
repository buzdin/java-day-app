package lv.jug.javaday.androidapp.presentation.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SchedulePageAdapter extends FragmentStatePagerAdapter {

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

    public int getItemPosition(Object item) {
        RoomScheduleFragment fragment = (RoomScheduleFragment)item;
        String title = (String) fragment.getTitle();
        int position = findByTitle(fragments, title);

        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    }

    private int findByTitle(List<RoomScheduleFragment> fragments, String title) {
        for (int i = 0; i < fragments.size(); i++){
            RoomScheduleFragment fragment = fragments.get(i);
            if (fragment.getTitle().equals(title)){
                return i;
            }
        }

        return -1;
    }

    public void setFragments(List<RoomScheduleFragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}