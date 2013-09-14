package lv.jug.javaday.androidapp.presentation.speakers;

import android.os.Bundle;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

public class SpeakersDashboardActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speakers_dashboard);
    }

}
