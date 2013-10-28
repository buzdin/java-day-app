package lv.jug.javaday.androidapp.application;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Inject;

public class StringService {

    @Inject
    Context context;

    public String loadString(String name) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "string", context.getPackageName());
        return resources.getString(id);
    }

    public String loadString(int id) {
        Resources resources = context.getResources();
        return resources.getString(id);
    }

    public String[] loadStringArray(String name) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "array", context.getPackageName());
        return resources.getStringArray(id);
    }

    public String[] loadStringArray(int id) {
        Resources resources = context.getResources();
        return resources.getStringArray(id);
    }
}
