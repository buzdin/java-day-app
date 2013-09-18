package lv.jug.javaday.androidapp.presentation.speaker;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpeakerDashboardActivity extends BaseActivity {

    @Inject
    SpeakerAdapter adapter;

    @InjectView(R.id.speakers)
    ListView speakers;

    @Override
    protected int contentViewId() {
        return R.layout.speaker_dashboard;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        adapter.setData(mockData);
        speakers.setAdapter(adapter);
    }

    List<Speaker> mockData = Arrays.asList(
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info"),
            new Speaker("John Doe", "Doe company", "portrait", "Info")
    );

}
