package lv.jug.javaday.androidapp.presentation;

import android.app.Application;
import dagger.ObjectGraph;
import lv.jug.javaday.androidapp.infrastructure.dagger.DaggerModule;
import lv.jug.javaday.androidapp.infrastructure.dagger.MainModule;

import java.util.Arrays;
import java.util.List;

public class BaseApplication extends Application {

    private static ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        DaggerModule[] modules = getModules();
        objectGraph = ObjectGraph.create(modules);
    }

    public static <T> void inject(T instance) {
        if(objectGraph != null) objectGraph.inject(instance);
    }

    public DaggerModule[] getModules() {
        List<DaggerModule> modules = Arrays.<DaggerModule>asList(
                new MainModule(this)
        );
        return modules.toArray(new DaggerModule[modules.size()]);
    }
}
