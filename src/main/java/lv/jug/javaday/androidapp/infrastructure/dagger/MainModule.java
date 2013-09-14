package lv.jug.javaday.androidapp.infrastructure.dagger;

import android.content.Context;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import lv.jug.javaday.androidapp.infrastructure.BaseApplication;
import lv.jug.javaday.androidapp.presentation.speakers.SpeakersDashboardActivity;

import javax.inject.Singleton;

@Module(
        entryPoints = {
                BaseApplication.class,
                SpeakersDashboardActivity.class
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
