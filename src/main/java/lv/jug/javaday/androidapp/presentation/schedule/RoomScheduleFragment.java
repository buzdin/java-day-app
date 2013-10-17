package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import javax.inject.Inject;
import java.util.ArrayList;

public class RoomScheduleFragment extends BaseFragment {

    private static final String TITLE_KEY = "title";
    private static final String EVENTS_KEY = "events";

    @Inject
    ScheduleAdapter adapter;

    @InjectView(R.id.event)
    ListView listView;

    private String title;

    private ArrayList<Event> events;

    @Override
    protected int contentViewId() {
        return R.layout.room_schedule;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        adapter.setData(events);
        listView.setAdapter(adapter);
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
