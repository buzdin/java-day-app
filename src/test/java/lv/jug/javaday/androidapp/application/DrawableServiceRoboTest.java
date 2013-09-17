package lv.jug.javaday.androidapp.application;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import dagger.Module;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.DrawableService;
import lv.jug.javaday.androidapp.presentation.BaseRobolectricTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Module(
        includes = {BaseRobolectricTest.class },
        entryPoints = {DrawableServiceRoboTest.class}
)
public class DrawableServiceRoboTest extends BaseRobolectricTest {

    @Inject
    Context context;

    @Inject
    DrawableService service;

    @Test
    public void shouldFindPortraitdrawable() {
        Drawable expected = context.getResources().getDrawable(R.drawable.portrait);
        Drawable actual = service.loadDrawableByName("portrait");

        assertThat(actual.toString(), equalTo(expected.toString()));
    }
}
