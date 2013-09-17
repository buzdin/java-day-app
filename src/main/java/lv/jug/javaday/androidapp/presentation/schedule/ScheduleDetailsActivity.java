package lv.jug.javaday.androidapp.presentation.schedule;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.domain.SpeakerBuilder;
import lv.jug.javaday.androidapp.presentation.BaseActivity;

import javax.inject.Inject;

public class ScheduleDetailsActivity extends BaseActivity {

    @Override
    protected int contentViewId() {
        return R.layout.schedule_details;
    }
}
