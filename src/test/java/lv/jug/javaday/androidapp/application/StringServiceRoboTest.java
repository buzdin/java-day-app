package lv.jug.javaday.androidapp.application;

import android.content.Context;
import dagger.Module;
import lv.jug.javaday.androidapp.BaseRobolectricTest;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.infrastructure.dagger.DaggerModule;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.junit.Assert.assertThat;

public class StringServiceRoboTest extends BaseRobolectricTest {

    @Inject
    Context context;

    @Inject
    StringService service;

    @Test
    public void shouldFindString() {
        String expected = context.getResources().getString(R.string.where);
        String actual = service.loadString("where");

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldFindStringArray() {
        String[] expected = context.getResources().getStringArray(R.array.navigation_drawer);
        String[] actual = service.loadStringArray("navigation_drawer");

        assertThat(actual, arrayContaining(expected));
    }

    @Override
    public DaggerModule getModule() {
        return new TestModule();
    }

    @Module(includes = BaseTestModule.class, injects = StringServiceRoboTest.class)
    static class TestModule implements DaggerModule {}
}
