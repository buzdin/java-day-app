package lv.jug.javaday.androidapp.presentation.speakers;

import android.os.Bundle;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

public class SpeakerDetailsActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_details);
    }

}
