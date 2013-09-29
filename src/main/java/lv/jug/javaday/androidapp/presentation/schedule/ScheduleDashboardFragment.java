package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.viewpagerindicator.TitlePageIndicator;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleDashboardFragment extends BaseFragment {

    @InjectView(R.id.viewpager)
    ViewPager pager;

    @InjectView(R.id.titles)
    TitlePageIndicator pageIndicator;

    SchedulePageAdapter pageAdapter;

    @Override
    protected int contentViewId() {
        return R.layout.schedule_dashboard;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        final ActionBar bar = getActionBar();
//        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        List<RoomScheduleFragment> fragments = getFragments();
        pageAdapter = new SchedulePageAdapter(((FragmentActivity) getActivity()).getSupportFragmentManager(), fragments);
        pager.setAdapter(pageAdapter);

        pageIndicator.setViewPager(pager);
    }

    private List<RoomScheduleFragment> getFragments(){
        List<RoomScheduleFragment> fragments = new ArrayList<RoomScheduleFragment>();

        ArrayList<Event> events1 = new ArrayList<Event>();
        events1.add(new Event());

        ArrayList<Event> events2 = new ArrayList<Event>();
        events2.add(new Event());
        events2.add(new Event());

        ArrayList<Event> events3 = new ArrayList<Event>();
        events3.add(new Event());
        events3.add(new Event());
        events3.add(new Event());

        fragments.add(new RoomScheduleFragment("Room 4", events1));
        fragments.add(new RoomScheduleFragment("Room 5", events2));
        fragments.add(new RoomScheduleFragment("Room 6", events3));

        return fragments;
    }
}
