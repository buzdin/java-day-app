package lv.jug.javaday.androidapp.presentation.schedule;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerBuilder;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

public class ScheduleDetailsActivity extends BaseActivity {

    @InjectView(R.id.speakername)
    TextView speakerName;

    @InjectView(R.id.speakercompany)
    TextView speakerCompany;

    @InjectView(R.id.speakerphoto)
    TextView speakerPhoto;

    @InjectView(R.id.speakerinfo)
    TextView speakerInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_details);

        showSpeaker(new SpeakerBuilder().createSpeaker());
    }

    private void showSpeaker(Speaker speaker) {
        speakerName.setText(speaker.getName());
        speakerCompany.setText(speaker.getCompany());
        String photo = speaker.getPhoto();
        speakerInfo.setText(speaker.getInfo());
    }

}
