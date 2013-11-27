package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import butterknife.InjectView;
import com.loopj.android.http.JsonHttpResponseHandler;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.common.FeedbackService;
import lv.jug.javaday.androidapp.domain.Event;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerRepository;
import lv.jug.javaday.androidapp.domain.Vote;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakerDetailsFragment;
import org.apache.http.HttpEntity;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleDetailsFragment extends BaseFragment {
    private static ClassLogger logger = new ClassLogger(ScheduleDetailsFragment.class);

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
	@InjectView(R.id.comment_to_speaker)
	EditText commentToSpeaker;
	@InjectView(R.id.vote_bad)
	ImageButton voteBadButton;
	@InjectView(R.id.vote_good)
	ImageButton voteGoodButton;
	@InjectView(R.id.vote_excellent)
	ImageButton voteExcellentButton;
	@InjectView(R.id.feedbackSuccessGroup)
	LinearLayout successGroup;
	@InjectView(R.id.feedbackGroup)
	LinearLayout feedbackGroup;

	@Override
	protected int contentViewId() {
		return R.layout.schedule_details;
	}

	@Override
	protected void init(Bundle bundle) {
		getActivity().getWindow()
				.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		Event event = getArguments().getParcelable(KEY_EVENT);

		initOnClickListeners(event.getSessionId());

		showEvent(event);
	}

	private void sendVote(int rate, int sessionId) {
		String comment = commentToSpeaker.getText().toString().trim();
		HttpEntity entity = Vote.create(rate, sessionId, comment);

		FeedbackService.post(getActivity().getApplicationContext(), entity, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				//TODO: implement save in shared preferences and limit vote to 1 time for session
				feedbackGroup.setVisibility(View.GONE);
				successGroup.setVisibility(View.VISIBLE);
			}

            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {
                logger.w("Failed to send Vote");
            }
        });
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

	private void initOnClickListeners(final int sessionId) {
		View.OnClickListener onClickListener = new View.OnClickListener() {
			private Timer timer = new Timer();
			private boolean showToast = true;
			@Override
			public void onClick(View view) {
				if (isNetworkAvailable()) {
					sendVote(getRating(view), sessionId);
				} else if (showToast) {
					showToast = false;
					Toast.makeText(getActivity().getBaseContext(), getString(R.string.check_internet), Toast.LENGTH_SHORT).show();

					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							showToast = true;
						}
					}, 5000);
				}
			}

			private int getRating(View view) {
				if (view.equals(voteBadButton)) {
					return Vote.BAD;
				} else if (view.equals(voteGoodButton)) {
					return Vote.GOOD;
				}
				return Vote.EXCELLENT;
			}
		};
		voteBadButton.setOnClickListener(onClickListener);
		voteGoodButton.setOnClickListener(onClickListener);
		voteExcellentButton.setOnClickListener(onClickListener);
	}
}
