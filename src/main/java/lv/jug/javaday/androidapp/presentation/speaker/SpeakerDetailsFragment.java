package lv.jug.javaday.androidapp.presentation.speaker;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.common.StringUtils;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.EventRepository;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDetailsFragment;

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

    @InjectView(R.id.speakertwitter)
    TextView speakerTwitter;

    @InjectView(R.id.speakerpresentation)
    TextView speakerPresentation;

	@InjectView(R.id.twitter_button_group)
	LinearLayout twitterButtonGroup;

    @Override
    protected int contentViewId() {
        return R.layout.speaker_details;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        Speaker speaker = getArguments().getParcelable(KEY_SPEAKER);
        showSpeaker(speaker);
    }

    private void showSpeaker(final Speaker speaker) {
        Drawable portrait = drawableService.loadDrawable(speaker.getPhoto());
        Drawable countryFlag = drawableService.loadDrawable(speaker.getCountry());

        final Event presentation = eventRepository.loadForSpeaker(speaker);
        String presentationTitle = presentation.getTitle();

        final String twitterName = speaker.getTwitter();
	    if (StringUtils.isEmpty(twitterName)) {
		    twitterButtonGroup.setVisibility(View.GONE);
	    } else {
		    twitterButtonGroup.setVisibility(View.VISIBLE);
		    String twitter = "Follow @" + twitterName;
		    speakerTwitter.setText(twitter);
	    }

        speakerName.setText(speaker.getName());
        speakerFlag.setImageDrawable(countryFlag);
        speakerInfo.setText(speaker.getInfo());
        speakerPhoto.setImageDrawable(portrait);
        speakerCompany.setText(speaker.getCompany());
        speakerPresentation.setText(presentationTitle);

	    twitterButtonGroup.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
		        showSpeakerTwitter(twitterName);
	        }
        });
        speakerPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPresentationDetails(presentation);
            }
        });
    }

    private void showPresentationDetails(Event presentation) {
        Bundle data = new Bundle();
        data.putParcelable(ScheduleDetailsFragment.KEY_EVENT, presentation);

        ScheduleDetailsFragment fragment = new ScheduleDetailsFragment();
        fragment.setArguments(data);

        getMainActivity().changeFragment(fragment);
    }

    private void showSpeakerTwitter(String twitter) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitter)));
    }
}
