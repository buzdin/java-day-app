package lv.jug.javaday.androidapp.presentation.common;

import android.content.Context;
import android.widget.BaseAdapter;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter extends BaseAdapter {

    @Inject
    Context context;

    private List data;

    @Override
    public int getCount() {
        return getData().size();
    }

    @Override
    public Object getItem(int position) {
        return getData().get(position);
    }

    public <T> T get(int position) {
        return (T) getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public <T> void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public <T> List<T> getData() {
        return data;
    }

    public Context getContext() {
        return context;
    }
}
