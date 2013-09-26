package lv.jug.javaday.androidapp.infrastructure.dagger;

import android.content.Context;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import lv.jug.javaday.androidapp.presentation.BaseApplication;
import lv.jug.javaday.androidapp.presentation.MainActivity;
import lv.jug.javaday.androidapp.presentation.home.HomeFragment;
import lv.jug.javaday.androidapp.presentation.schedule.RoomScheduleFragment;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDashboardFragment;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDetailsFragment;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakerDashboardFragment;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakerDetailsFragment;

import javax.inject.Singleton;

@Module(
        entryPoints = {
                BaseApplication.class,
                MainActivity.class,
                HomeFragment.class,
                RoomScheduleFragment.class,
                SpeakerDashboardFragment.class,
                SpeakerDetailsFragment.class,
                ScheduleDashboardFragment.class,
                ScheduleDetailsFragment.class
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
