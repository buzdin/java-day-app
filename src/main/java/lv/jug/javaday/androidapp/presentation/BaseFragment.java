package lv.jug.javaday.androidapp.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Views;
import com.actionbarsherlock.app.SherlockFragment;
import com.squareup.otto.Bus;
import lv.jug.javaday.androidapp.common.StringUtils;

import javax.inject.Inject;

public abstract class BaseFragment extends SherlockFragment {

    @Inject
    Bus bus;

	private ProgressDialog pd;

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
	    if (!StringUtils.isEmpty(this.getTag())) {
		    getActivity().setTitle(this.getTag());
	    }
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    protected void init(Bundle savedInstanceState){}

    protected abstract int contentViewId();

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }


	public void showProgressDialog() {
		pd = new ProgressDialog(getActivity());
		pd.setMessage("Please wait");
		pd.setCancelable(false);
		pd.show();
	}

	public void hideProgressDialog() {
		if (pd != null) {
			pd.hide();
			pd = null;
		}
	}
}
