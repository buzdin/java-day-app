package lv.jug.javaday.androidapp.presentation.navigation;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.DrawableService;
import lv.jug.javaday.androidapp.common.StringService;
import lv.jug.javaday.androidapp.presentation.common.BaseListAdapter;

import javax.inject.Inject;
import java.util.Arrays;

public class NavigationDrawerAdapter extends BaseListAdapter<NavigationItem> {

    @Inject
    StringService stringService;

    @Inject
    DrawableService drawableService;

    public NavigationDrawerAdapter() {
        setData(Arrays.asList(
                new NavigationItem("home"),
                new NavigationItem("schedule"),
                new NavigationItem("speakers"),
                new NavigationItem("twitter")
        ));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.drawer_list_item, parent, false);
        NavigationItem item = get(position);

        TextView titleV = Views.findById(view, R.id.navigation_title);
        String title = stringService.loadString(item.getTitleName());
        titleV.setText(title);

        ImageView moreButton = Views.findById(view, R.id.navigation_icon);
        Drawable drawable = drawableService.loadDrawable(item.getIconName());
        moreButton.setImageDrawable(drawable);

        return view;
    }
}
