package lv.jug.javaday.androidapp.application;

import android.content.res.Resources;

import javax.inject.Inject;

public class StringService extends AndroidService {

    @Inject
    public StringService() {}

    public String loadString(String name) {
        Resources resources = getContext().getResources();
        int id = resources.getIdentifier(name, "string", getContext().getPackageName());
        return resources.getString(id);
    }

    public String loadString(int id) {
        Resources resources = getContext().getResources();
        return resources.getString(id);
    }

    public String[] loadStringArray(String name) {
        Resources resources = getContext().getResources();
        int id = resources.getIdentifier(name, "array", getContext().getPackageName());
        return resources.getStringArray(id);
    }

    public String[] loadStringArray(int id) {
        Resources resources = getContext().getResources();
        return resources.getStringArray(id);
    }

    public String loadStringArrayItem(int id, int index) {
        return loadStringArray(id)[index];
    }

    public String loadStringArrayItem(String name, int index) {
        return loadStringArray(name)[index];
    }
}
