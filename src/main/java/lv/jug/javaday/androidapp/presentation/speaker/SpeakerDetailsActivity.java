package lv.jug.javaday.androidapp.presentation.speaker;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerBuilder;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

import javax.inject.Inject;

public class SpeakerDetailsActivity extends BaseActivity {

    public static final String KEY_SPEAKER = "speaker";

    @Inject
    DrawableService drawableService;

    @InjectView(R.id.speakername)
    TextView speakerName;

    @InjectView(R.id.speakercompany)
    TextView speakerCompany;

    @InjectView(R.id.speakerphoto)
    ImageView speakerPhoto;

    @InjectView(R.id.speakerinfo)
    TextView speakerInfo;

    @Override
    protected int contentViewId() {
        return R.layout.speaker_details;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        Speaker speaker = getParcelableFromIntent(KEY_SPEAKER);
        showSpeaker(speaker);
    }

    private void showSpeaker(Speaker speaker) {
        Drawable portrait = drawableService.loadDrawableByName(speaker.getPhoto());

        speakerName.setText(speaker.getName());
        speakerCompany.setText(speaker.getCompany());
        speakerPhoto.setImageDrawable(portrait);
        speakerInfo.setText(speaker.getInfo());
    }
}
