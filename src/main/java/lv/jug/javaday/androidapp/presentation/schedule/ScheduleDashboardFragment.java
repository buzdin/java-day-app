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

    private List<RoomScheduleFragment> createFragments(){
        List<RoomScheduleFragment> fragments = new ArrayList<RoomScheduleFragment>();

        RoomScheduleFragment fragment1 = new RoomScheduleFragment();
        fragment1.setTitle("Room 4");
        fragment1.setRoomId(4);
        fragments.add(fragment1);


        RoomScheduleFragment fragment2 = new RoomScheduleFragment();
        fragment2.setTitle("Room 5");
        fragment2.setRoomId(5);
        fragments.add(fragment2);

        RoomScheduleFragment fragment3 = new RoomScheduleFragment();
        fragment3.setTitle("Room 6");
        fragment3.setRoomId(6);
        fragments.add(fragment3);

        return fragments;
    }


    public void notifyDataSetChanged() {
        pager.invalidate();
        pageAdapter.setFragments(createFragments());
        pager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
