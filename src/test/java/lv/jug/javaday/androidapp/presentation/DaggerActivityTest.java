package lv.jug.javaday.androidapp.presentation;

import android.widget.TextView;
import dagger.Module;
import dagger.Provides;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.MyStringProvider;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@Module(
        includes = {BaseRobolectricTest.class },
        entryPoints = {DaggerActivityTest.class}
)
public class DaggerActivityTest extends BaseRobolectricTest {

    @Inject
    HomeActivity activity;

    @Mock
    MyStringProvider stringProvider;

    @Test
    public void shouldHaveAnAppName() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("android_mvn_template"));
    }

    @Test
    public void shouldSetTextViewValue() throws Exception {
        when(stringProvider.getString()).thenReturn("Injected");

        activity.onCreate(null);
        TextView textView = (TextView) activity.findViewById(R.id.textView);

        assertThat(textView.getText().toString(), equalTo("Injected"));
    }

    @Provides
    MyStringProvider provideDaggerStringProvider(){
        return stringProvider;
    }
}