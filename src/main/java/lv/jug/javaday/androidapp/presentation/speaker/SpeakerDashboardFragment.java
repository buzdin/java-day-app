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
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import lv.jug.javaday.androidapp.presentation.MainActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class SpeakerDashboardFragment extends BaseFragment implements AdapterView.OnItemClickListener {

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

    // TODO: Stub. Should be deleted later
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

        Bundle data = new Bundle();
        data.putParcelable(SpeakerDetailsFragment.KEY_SPEAKER, speaker);

        SpeakerDetailsFragment fragment = new SpeakerDetailsFragment();
        fragment.setArguments(data);

        ((MainActivity) getActivity()).changeFragment(fragment);
    }
}
