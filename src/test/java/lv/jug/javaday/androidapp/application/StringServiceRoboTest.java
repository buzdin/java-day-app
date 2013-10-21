package lv.jug.javaday.androidapp.application;

import android.content.Context;
import android.graphics.drawable.Drawable;
import dagger.Module;
import lv.jug.javaday.androidapp.BaseRobolectricTest;
import lv.jug.javaday.androidapp.R;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Module(
        includes = {BaseRobolectricTest.class },
        entryPoints = {StringServiceRoboTest.class}
)
public class StringServiceRoboTest extends BaseRobolectricTest {

    @Inject
    Context context;

    @Inject
    StringService service;

    @Test
    public void shouldFindPortraitDrawable() {
        String expected = context.getResources().getString(R.string.where);
        String actual = service.loadString("where");

        assertThat(actual, equalTo(expected));
    }

}
