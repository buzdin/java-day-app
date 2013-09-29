package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.presentation.BaseApplication;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RoomScheduleFragment extends Fragment {

    private static final String TITLE_KEY = "title";
    private static final String EVENTS_KEY = "events";

    @Inject
    ScheduleAdapter adapter;

    @InjectView(R.id.event)
    ListView listView;

    private String title;

    private ArrayList<Event> events;

    public RoomScheduleFragment() {}

    public RoomScheduleFragment(String title, ArrayList<Event> events) {
        this.title = title;
        this.events = events;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseApplication.inject(this);
        View view = inflater.inflate(R.layout.room_schedule, container, false);
        Views.inject(this, view);

        adapter.setData(events);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, title);
        outState.putParcelableArrayList(EVENTS_KEY, events);
    }

    @Override
    public void onActivityCreated(Bundle inState) {
        super.onViewStateRestored(inState);

        if (inState != null) {
            title = inState.getString(TITLE_KEY);
            events = inState.getParcelableArrayList(EVENTS_KEY);
        }
    }

    public CharSequence getTitle() {
        return title;
    }
}
