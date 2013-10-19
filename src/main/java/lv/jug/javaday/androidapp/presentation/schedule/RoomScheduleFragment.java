package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.EventRepository;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RoomScheduleFragment extends BaseFragment {

    private static final String TITLE_KEY = "title";
    private static final String EVENTS_KEY = "events";

    @Inject
    ScheduleAdapter adapter;

    @Inject
    EventRepository repository;

    @InjectView(R.id.event)
    ListView listView;

    private int roomId;
    private String title;

    @Override
    protected int contentViewId() {
        return R.layout.room_schedule;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        List<Event> events = repository.loadByRoom(roomId);

        adapter.setData(events);
        listView.setAdapter(adapter);
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
