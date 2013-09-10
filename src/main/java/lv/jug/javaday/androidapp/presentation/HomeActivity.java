package lv.jug.javaday.androidapp.presentation;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.MyStringProvider;
import lv.jug.javaday.androidapp.infrastructure.BaseActivity;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

    @Inject
    MyStringProvider stringProvider;

    @InjectView(R.id.textView)
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Views.inject(this);

        textView.setText(stringProvider.getString());
    }

}
