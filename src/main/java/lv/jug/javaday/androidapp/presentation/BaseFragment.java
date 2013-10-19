package lv.jug.javaday.androidapp.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    protected abstract int contentViewId();

    protected abstract void init(Bundle savedInstanceState);

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

}
