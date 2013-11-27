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
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.common.StringUtils;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.EventRepository;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDetailsFragment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @InjectView(R.id.presentation_panel)
    LinearLayout presentationPanel;

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

        List<Event> presentations = eventRepository.loadForSpeaker(speaker);
        presentations = filterSameTopics(presentations);

        for (Event event : presentations) {
            renderPresentation(event);
        }

        final String twitterName = speaker.getTwitter();
	    if (StringUtils.isEmpty(twitterName)) {
		    twitterButtonGroup.setVisibility(View.GONE);
	    } else {
		    twitterButtonGroup.setVisibility(View.VISIBLE);
		    String twitter = "Follow @" + twitterName;
		    speakerTwitter.setText(twitter);
	    }
        twitterButtonGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpeakerTwitter(twitterName);
            }
        });

        speakerName.setText(speaker.getName());
        speakerFlag.setImageDrawable(countryFlag);
        speakerInfo.setText(speaker.getInfo());
        speakerPhoto.setImageDrawable(portrait);
        speakerCompany.setText(speaker.getCompany());
    }

    private void renderPresentation(final Event event) {
        View panel = getActivity().getLayoutInflater().inflate(R.layout.presentation_title_bubble, null);
        String presentationTitle = event.getTitle();

        TextView speakerPresentation = Views.findById(panel, R.id.speaker_presentation);
        speakerPresentation.setText(presentationTitle);
        speakerPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPresentationDetails(event);
            }
        });
        presentationPanel.addView(panel);
    }

    private List<Event> filterSameTopics(List<Event> presentations) {
        Map<String, Event> map = new HashMap<String, Event>();
        for (Event presentation : presentations) {
            String title = presentation.getTitle();
            if(!map.containsKey(title)) {
                map.put(title, presentation);
            }
        }
        return new ArrayList<Event>(map.values());
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
