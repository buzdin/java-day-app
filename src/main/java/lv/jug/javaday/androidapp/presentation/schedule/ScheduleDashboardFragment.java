package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import butterknife.InjectView;
import com.viewpagerindicator.TitlePageIndicator;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        pageAdapter = new SchedulePageAdapter((getActivity()).getSupportFragmentManager(), createFragments());
        pager.setAdapter(pageAdapter);

        pageIndicator.setViewPager(pager);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        init(savedInstanceState);
    }

    private List<RoomScheduleFragment> createFragments(){
        List<RoomScheduleFragment> fragments = new ArrayList<RoomScheduleFragment>();

        fragments.add(RoomScheduleFragment.newInstance(4, "Room 4"));
        fragments.add(RoomScheduleFragment.newInstance(5, "Room 5"));
        fragments.add(RoomScheduleFragment.newInstance(6, "Room 6"));

        return fragments;
    }
}
