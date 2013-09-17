package lv.jug.javaday.androidapp.presentation.speaker;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

import javax.inject.Inject;

public class SpeakerDashboardActivity extends BaseActivity {

    @Inject
    SpeakerAdapter adapter;

    @InjectView(R.id.speakers)
    ListView speakers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_dashboard);

        speakers.setAdapter(adapter);
    }

}
