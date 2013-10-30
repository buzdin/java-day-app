package lv.jug.javaday.androidapp.presentation.speaker;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.EventRepository;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.presentation.BaseFragment;

import javax.inject.Inject;

public class SpeakerDetailsFragment extends BaseFragment {

    public static final String KEY_SPEAKER = "speaker";

    @Inject
    DrawableService drawableService;

    @Inject
    EventRepository eventRepository;

    @InjectView(R.id.speakername)
    TextView speakerName;

    @InjectView(R.id.speakercompany)
    TextView speakerCompany;

    @InjectView(R.id.speakerphoto)
    ImageView speakerPhoto;

    @InjectView(R.id.speakerflag)
    ImageView speakerFlag;

    @InjectView(R.id.speakerinfo)
    TextView speakerInfo;

    @InjectView(R.id.speakerpresentation)
    TextView speakerPresentation;

    @Override
    protected int contentViewId() {
        return R.layout.speaker_details;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        Speaker speaker = getArguments().getParcelable(KEY_SPEAKER);
        showSpeaker(speaker);
    }

    private void showSpeaker(Speaker speaker) {
        Drawable portrait = drawableService.loadDrawable(speaker.getPhoto());
        Drawable countryFlag = drawableService.loadDrawable(speaker.getCountry());

        // TODO: Should start ScheduleDetailsFragment
        Event presentation = eventRepository.loadForSpeaker(speaker);
        String presentationTitle = presentation.getTitle();

        speakerName.setText(speaker.getName());
        speakerFlag.setImageDrawable(countryFlag);
        speakerInfo.setText(speaker.getInfo());
        speakerPhoto.setImageDrawable(portrait);
        speakerCompany.setText(speaker.getCompany());
        speakerPresentation.setText(presentationTitle);
    }
}
