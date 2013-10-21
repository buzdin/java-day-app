package lv.jug.javaday.androidapp.presentation.speaker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.domain.Speaker;
import lv.jug.javaday.androidapp.presentation.common.BaseListAdapter;

import javax.inject.Inject;

public class SpeakerAdapter extends BaseListAdapter<Speaker> {

    @Inject
    DrawableService drawableService;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Speaker speaker = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.speaker_row, parent, false);

        ImageView photo = Views.findById(view, R.id.speakerphoto);
        photo.setImageDrawable(drawableService.loadDrawable(speaker.getPhoto()));

        TextView name = Views.findById(view, R.id.speakername);
        name.setText(speaker.getName());

        TextView company = Views.findById(view, R.id.speakercompany);
        company.setText(speaker.getCompany());

        return view;
    }
}
