package lv.jug.javaday.androidapp.presentation.speaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerBuilder;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpeakerDashboardActivity extends BaseActivity implements AdapterView.OnItemClickListener {

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
        speakers.setOnItemClickListener(this);
        speakers.setAdapter(adapter);
    }

    List<Speaker> mockData = Arrays.asList(
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker(),
            new SpeakerBuilder().createSpeaker()
    );

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Speaker speaker = adapter.getItem(position);
        Intent intent = new Intent(this, SpeakerDetailsActivity.class);
        intent.putExtra(SpeakerDetailsActivity.KEY_SPEAKER, speaker);

        startActivity(intent);
    }
}
