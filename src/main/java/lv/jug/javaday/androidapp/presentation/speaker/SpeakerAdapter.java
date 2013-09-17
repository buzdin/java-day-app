package lv.jug.javaday.androidapp.presentation.speaker;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.actionbarsherlock.R;
import lv.jug.javaday.androidapp.Speaker;

import javax.inject.Inject;

public class SpeakerAdapter extends ArrayAdapter<Speaker> {

    @Inject
    public SpeakerAdapter(Context context) {
        super(context, R.layout.speaker_row);
    }

}
