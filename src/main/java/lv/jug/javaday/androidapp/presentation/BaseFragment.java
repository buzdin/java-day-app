package lv.jug.javaday.androidapp.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.Views;
import com.squareup.otto.Bus;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.infrastructure.BaseApplication;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    @Inject
    Bus bus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseApplication.inject(this);
        View view = inflater.inflate(contentViewId(), container, false);
        Views.inject(this, view);
//        init(state);
        return view;
    }

    protected abstract int contentViewId();

}
