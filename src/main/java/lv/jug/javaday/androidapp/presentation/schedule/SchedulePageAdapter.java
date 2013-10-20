package lv.jug.javaday.androidapp.presentation.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

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

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//        fragments.remove(position);
//    }

//    @Override
//    public void destroyItem(ViewGroup collection, int position, Object view) {
//        super.destroyItem(collection, position, view);
//        collection.removeView((View) view);
//        ((Fragment) view).
//    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return fragments.get(i).getTitle();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(List<RoomScheduleFragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}