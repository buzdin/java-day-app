package lv.jug.javaday.androidapp.application;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import javax.inject.Inject;

public class DrawableService extends AndroidService {

    @Inject
    public DrawableService() {}

    public Drawable loadDrawable(String photo) {
        Resources resources = getContext().getResources();
        int id = resources.getIdentifier(photo, "drawable", getContext().getPackageName());
        return resources.getDrawable(id);
    }

    public Drawable loadDrawable(int photo) {
        Resources resources = getContext().getResources();
        return resources.getDrawable(photo);
    }
}
