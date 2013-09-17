package lv.jug.javaday.androidapp.application;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import javax.inject.Inject;

public class DrawableService {

    @Inject
    Context context;

    public Drawable loadDrawableByName(String photo) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(photo, "drawable", context.getPackageName());
        return resources.getDrawable(id);
    }
}
