package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerRepository;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakerDetailsFragment;

import javax.inject.Inject;
import java.util.List;

public class ScheduleDetailsFragment extends BaseFragment {

	public static final String KEY_EVENT = "event";

    @Inject
	DrawableService drawableService;

	@Inject
	SpeakerRepository speakerRepository;

	@InjectView(R.id.speaker_photo_1)
	ImageView speakerPhoto1;

	@InjectView(R.id.speaker_name_1)
	TextView speakerName1;

	@InjectView(R.id.speaker_photo_2)
	ImageView speakerPhoto2;

	@InjectView(R.id.speaker_name_2)
	TextView speakerName2;

	@InjectView(R.id.speaker_separator)
	ImageView speakerSeparator;

	@InjectView(R.id.speaker_group_1)
	LinearLayout speakerGroup1;

	@InjectView(R.id.speaker_group_2)
	LinearLayout speakerGroup2;

	@InjectView(R.id.event_info)
	TextView eventInfo;

	@InjectView(R.id.event_title)
	TextView eventTitle;

	@InjectView(R.id.event_description)
	TextView eventDescription;

	@Override
	protected int contentViewId() {
		return R.layout.schedule_details;
	}

	@Override
	protected void init(Bundle bundle) {
		Event event = getArguments().getParcelable(KEY_EVENT);
		showEvent(event);
	}

	private void showEvent(Event event) {
		eventTitle.setText(event.getTitle());
		String eventInfoText = String.format("%s - Room %d", event.getStartingTime(), event.getRoomId());
		eventInfo.setText(eventInfoText);
		eventDescription.setText(event.getDescription());

		List<Speaker> speakers = speakerRepository.load(event.getSpeakerName());

		populateFirstSpeaker(speakers.get(0));

		if (speakers.size() == 2) {
			populateSecondSpeaker(speakers.get(1));
		}
	}

	private void populateFirstSpeaker(final Speaker speaker) {
		speakerName1.setText(speaker.getName());
		speakerPhoto1.setImageDrawable(drawableService.loadDrawable(speaker.getPhoto()));

		speakerGroup1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showSpeaker(speaker);
			}
		});
	}

	private void populateSecondSpeaker(final Speaker speaker) {
		speakerSeparator.setVisibility(View.VISIBLE);
		speakerGroup2.setVisibility(View.VISIBLE);

		speakerName2.setText(speaker.getName());
		speakerPhoto2.setImageDrawable(drawableService.loadDrawable(speaker.getPhoto()));

		speakerGroup2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showSpeaker(speaker);
			}
		});
	}

	public void showSpeaker(Speaker speaker) {
		Bundle data = new Bundle();
		data.putParcelable(SpeakerDetailsFragment.KEY_SPEAKER, speaker);

		SpeakerDetailsFragment fragment = new SpeakerDetailsFragment();
		fragment.setArguments(data);

		getMainActivity().changeFragment(fragment);
	}
}
