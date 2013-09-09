package lv.jug.javaday.androidapp.infrastructure;

import android.app.Application;
import dagger.ObjectGraph;
import lv.jug.javaday.androidapp.infrastructure.dagger.DaggerModule;

public class BaseApplication extends Application {

    private static ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new DaggerModule(this));
    }

    public static <T> void inject(T instance) {
        if(objectGraph != null) objectGraph.inject(instance);
    }
}
