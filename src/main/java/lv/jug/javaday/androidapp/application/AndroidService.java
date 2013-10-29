package lv.jug.javaday.androidapp.application;

import android.content.Context;

import javax.inject.Inject;

public class AndroidService {

    @Inject
    Context context;

    public Context getContext() {
        return context;
    }

    public AndroidService withContext(Context context) {
        this.context = context;
        return this;
    }
}
