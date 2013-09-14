package lv.jug.javaday.androidapp.infrastructure.dagger;

import android.content.Context;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import lv.jug.javaday.androidapp.infrastructure.BaseApplication;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDashboardActivity;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDetailsActivity;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakerDetailsActivity;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakersDashboardActivity;

import javax.inject.Singleton;

@Module(
        entryPoints = {
                BaseApplication.class,
                SpeakersDashboardActivity.class,
                SpeakerDetailsActivity.class,
                ScheduleDashboardActivity.class,
                ScheduleDetailsActivity.class
        }
)
public class MainModule implements DaggerModule {
    private final Context appContext;

    public MainModule(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    @Provides @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides Context provideContext() {
        return appContext;
    }

}
