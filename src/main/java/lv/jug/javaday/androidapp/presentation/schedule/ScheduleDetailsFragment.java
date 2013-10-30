package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.SpeakerRepository;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import javax.inject.Inject;

public class ScheduleDetailsFragment extends BaseFragment {

    public static final String KEY_EVENT = "event";

    @Inject
    DrawableService drawableService;

    @Inject
    SpeakerRepository speakerRepository;

    @InjectView(R.id.speaker_name)
    TextView speakerName;

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
        String speakerId = event.getSpeakerName();
        speakerName.setText(speakerId);

        eventTitle.setText(event.getTitle());
        eventDescription.setText(event.getDescription());
    }
}
