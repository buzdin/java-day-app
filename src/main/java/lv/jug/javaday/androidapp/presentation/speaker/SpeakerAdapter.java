package lv.jug.javaday.androidapp.presentation.speaker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.OnClick;
import butterknife.Views;
import com.actionbarsherlock.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Speaker;

import javax.inject.Inject;
import java.util.List;

public class SpeakerAdapter extends BaseAdapter {

    private Context context;

    private List<Speaker> data;

    @Inject
    DrawableService drawableService;

    @Inject
    public SpeakerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Speaker getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Speaker speaker = data.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.speaker_row, parent, false);

        ImageView photo = Views.findById(view, R.id.speakerphoto);
        photo.setImageDrawable(drawableService.loadDrawableByName(speaker.getPhoto()));

        TextView name = Views.findById(view, R.id.speakername);
        name.setText(speaker.getName());

        TextView company = Views.findById(view, R.id.speakercompany);
        company.setText(speaker.getCompany());

        return view;
    }

    public void setData(List<Speaker> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
