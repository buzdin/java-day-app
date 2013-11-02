package lv.jug.javaday.androidapp.presentation.schedule;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.common.StringUtils;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.presentation.common.BaseListAdapter;

import javax.inject.Inject;

public class ScheduleAdapter extends BaseListAdapter {

	@Inject
	DrawableService drawableService;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.schedule_row, parent, false);
		Event event = get(position);

		ImageView icon = Views.findById(view, R.id.eventIcon);
		String iconName = event.getIcon();
		if (iconName != null) {
			Drawable drawable = drawableService.loadDrawable(iconName);
			icon.setImageDrawable(drawable);
		} else {
			icon.setVisibility(View.GONE);
		}

		TextView title = Views.findById(view, R.id.eventTitle);
		title.setText(event.getTitle());

		TextView time = Views.findById(view, R.id.eventTime);
		time.setText(event.getStartingTime());

		TextView speakerName = Views.findById(view, R.id.speakerName);
		if (!StringUtils.isEmpty(event.getSpeakerName())) {
			speakerName.setText(event.getSpeakerName());
		} else {
			speakerName.setVisibility(View.GONE);
		}


		ImageView moreButton = Views.findById(view, R.id.additionalDetailsImg);
		if (event.getDescription() != null) {
			Drawable drawable = drawableService.loadDrawable(R.drawable.people_arrow);
			moreButton.setImageDrawable(drawable);
		}

		return view;
	}
}
