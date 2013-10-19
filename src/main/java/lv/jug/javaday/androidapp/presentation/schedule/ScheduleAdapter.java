package lv.jug.javaday.androidapp.presentation.schedule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Event;

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
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_row, parent, false);
        Event event = data.get(position);

        ImageView icon = Views.findById(view, R.id.eventIcon);
        String iconName = event.getIcon();
        if(iconName != null) {
            Drawable drawable = drawableService.loadDrawableByName(iconName);
            icon.setImageDrawable(drawable);
        }

        TextView title = Views.findById(view, R.id.eventTitle);
        title.setText(event.getTitle());

        TextView time = Views.findById(view, R.id.eventTime);
        time.setText(event.getStartingTime());

        TextView speakerName = Views.findById(view, R.id.speakerName);
        speakerName.setText(event.getSpeakerName());

        // TODO: Some crazy bug here
//        ImageView moreButton = Views.findById(view, R.id.additionalDetailsImg);
//        if (event.getDescription() != null) {
//            Drawable drawable = context.getResources().getDrawable(R.drawable.button_more);
//            moreButton.setImageDrawable(drawable);
//        }

        return view;
    }

    public void setData(List<Event> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
