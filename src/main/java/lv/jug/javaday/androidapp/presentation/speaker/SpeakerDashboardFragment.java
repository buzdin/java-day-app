package lv.jug.javaday.androidapp.presentation.speaker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.InjectView;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.domain.DatabaseHelper;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerBuilder;
import lv.jug.javaday.androidapp.domain.SpeakerRepository;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import lv.jug.javaday.androidapp.presentation.MainActivity;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SpeakerDashboardFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @Inject
    SpeakerAdapter adapter;

    @Inject
    SpeakerRepository repository;

    @InjectView(R.id.speakers)
    ListView speakersListView;

    @Override
    protected int contentViewId() {
        return R.layout.speaker_dashboard;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        List<Speaker> speakers = repository.loadAll();
        adapter.setData(speakers);

        speakersListView.setOnItemClickListener(this);
        speakersListView.setAdapter(adapter);
    }

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
