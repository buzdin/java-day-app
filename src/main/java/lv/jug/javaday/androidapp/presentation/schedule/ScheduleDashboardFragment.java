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
        pageAdapter = new SchedulePageAdapter((getActivity()).getSupportFragmentManager(), fragments);
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

        RoomScheduleFragment fragment1 = new RoomScheduleFragment();
        fragment1.setTitle("Room 4");
        fragment1.setEvents(events1);
        fragment1.setRetainInstance(true);
        fragments.add(fragment1);


        RoomScheduleFragment fragment2 = new RoomScheduleFragment();
        fragment2.setTitle("Room 5");
        fragment2.setEvents(events2);
        fragment2.setRetainInstance(true);
        fragments.add(fragment2);

        RoomScheduleFragment fragment3 = new RoomScheduleFragment();
        fragment3.setTitle("Room 6");
        fragment3.setEvents(events3);
        fragment3.setRetainInstance(true);
        fragments.add(fragment3);

        return fragments;
    }
}
