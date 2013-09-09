package lv.jug.javaday.androidapp.infrastructure.dagger;

import android.content.Context;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import lv.jug.javaday.androidapp.infrastructure.BaseApplication;
import lv.jug.javaday.androidapp.presentation.HomeActivity;

import javax.inject.Singleton;

@Module(
        entryPoints = {
                BaseApplication.class,
                HomeActivity.class
        }
)
public class DaggerModule {
    private final Context appContext;

    public DaggerModule(Context appContext) {
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
