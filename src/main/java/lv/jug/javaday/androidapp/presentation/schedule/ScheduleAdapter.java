package lv.jug.javaday.androidapp.presentation.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.Speaker;

import javax.inject.Inject;
import java.util.List;

public class ScheduleAdapter extends BaseAdapter {

    private Context context;

    private List<Event> data;

    @Inject
    DrawableService drawableService;

    @Inject
    public ScheduleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Event getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = data.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.speaker_row, parent, false);

        return view;
    }

    public void setData(List<Event> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
