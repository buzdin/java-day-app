package lv.jug.javaday.androidapp.presentation.speaker;

import dagger.Module;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.BaseRobolectricTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertThat;

@Module(
        includes = {BaseRobolectricTest.class },
        entryPoints = {SpeakerDashboardFragmentTest.class}
)
public class SpeakerDashboardFragmentTest extends BaseRobolectricTest {

    @Inject
    SpeakerDashboardFragment activity;

    @Test
    public void shouldHaveAnAppName() throws Exception {
//        String appName = activity.getResources().getString(R.string.app_name);
//        assertThat(appName, equalTo("Javaday 2013"));
    }

}