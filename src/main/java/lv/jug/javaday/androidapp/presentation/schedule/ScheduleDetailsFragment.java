package lv.jug.javaday.androidapp.presentation.schedule;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.Speaker;
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

    @InjectView(R.id.speaker_photo)
    ImageView speakerPhoto;

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
        Speaker speaker = speakerRepository.loadByName(speakerId);

        speakerName.setText(speakerId);
        Drawable drawable = drawableService.loadDrawableByName(speaker.getPhoto());
        speakerPhoto.setImageDrawable(drawable);

        eventTitle.setText(event.getTitle());
        eventDescription.setText(event.getDescription());
    }
}
