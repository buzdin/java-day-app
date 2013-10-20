package lv.jug.javaday.androidapp.presentation.schedule;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.EventRepository;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import javax.inject.Inject;
import java.util.List;

public class RoomScheduleFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public static final String KEY_ROOM_ID = "roomId";
    public static final String KEY_TITLE = "title";

    @Inject
    ScheduleAdapter adapter;

    @Inject
    EventRepository repository;

    @InjectView(R.id.event)
    ListView listView;

    private int roomId;
    private String title;

    public static RoomScheduleFragment newInstance(int roomId, String title) {
        Bundle args = new Bundle();
        args.putInt(KEY_ROOM_ID, roomId);
        args.putString(KEY_TITLE, title);

        RoomScheduleFragment fragment = new RoomScheduleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int contentViewId() {
        return R.layout.room_schedule;
    }

    @Override
    protected void init(Bundle in) {
        Bundle bundle = in != null ? in : getArguments();
        if (bundle != null) {
            roomId = bundle.getInt(KEY_ROOM_ID);
            title = bundle.getString(KEY_TITLE);
        }

        List<Event> events = repository.loadByRoom(roomId);

        adapter.setData(events);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        setUserVisibleHint(true);
        out.putInt(KEY_ROOM_ID, roomId);
        out.putString(KEY_TITLE, title);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Event event = adapter.getItem(position);

        if(event.getDescription() != null) {
            Bundle data = new Bundle();
            data.putParcelable(ScheduleDetailsFragment.KEY_EVENT, event);

            ScheduleDetailsFragment fragment = new ScheduleDetailsFragment();
            fragment.setArguments(data);

            getMainActivity().changeFragment(fragment);
        }
    }

    public CharSequence getTitle() {
        return title;
    }
}
