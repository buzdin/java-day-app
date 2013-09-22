package lv.jug.javaday.androidapp.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Views;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    @Inject
    Bus bus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseApplication.inject(this);
        View view = inflater.inflate(contentViewId(), container, false);
        Views.inject(this, view);
        init(savedInstanceState);

        return view;
    }

    protected abstract int contentViewId();

    protected abstract void init(Bundle savedInstanceState);

}
