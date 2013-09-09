package lv.jug.javaday.androidapp.application;

import android.content.Context;
import lv.jug.javaday.androidapp.R;

import javax.inject.Inject;


public class MyStringProvider {

    Context context;

    @Inject
    public MyStringProvider(Context context) {
        this.context = context;
    }

    public String getString(){
        return context.getString(R.string.app_name);
    }

}
