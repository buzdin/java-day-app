package lv.jug.javaday.androidapp.presentation;

import android.app.Activity;
import android.os.Bundle;
import butterknife.Views;
import com.squareup.otto.Bus;
import lv.jug.javaday.androidapp.infrastructure.BaseApplication;

import javax.inject.Inject;

public class BaseActivity extends Activity {

    @Inject
    Bus bus;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);

        // Android constructs Activity instances so we must find the ObjectGraph
        // instance and inject this.
        BaseApplication.inject(this);
        Views.inject(this);
    }

    @Override protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }
}
