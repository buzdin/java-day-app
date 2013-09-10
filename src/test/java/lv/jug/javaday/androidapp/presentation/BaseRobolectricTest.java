package lv.jug.javaday.androidapp.presentation;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import dagger.Module;
import dagger.ObjectGraph;
import lv.jug.javaday.androidapp.infrastructure.dagger.DaggerModule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;


@Module(
        includes = {DaggerModule.class },
        overrides = true,
        entryPoints = {BaseRobolectricTest.class}
)
@Ignore
@RunWith(RobolectricTestRunner.class)
public class BaseRobolectricTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ObjectGraph.create(new DaggerModule(Robolectric.application), this).inject(this);
    }

}
