package lv.jug.javaday.androidapp.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.widget.*;
import butterknife.InjectView;
import butterknife.Views;
import com.squareup.otto.Bus;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.home.HomeFragment;
import lv.jug.javaday.androidapp.presentation.schedule.ScheduleDashboardFragment;
import lv.jug.javaday.androidapp.presentation.speaker.SpeakerDashboardFragment;

import javax.inject.Inject;

public class MainActivity extends Activity {

    @Inject
    Bus bus;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.left_drawer)
    ListView drawerList;

    CharSequence title;

    CharSequence drawerTitle;

    ActionBarDrawerToggle drawerToggle;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);

        // Android constructs Activity instances so we must find the ObjectGraph instance and inject this.
        BaseApplication.inject(this);
        setContentView(R.layout.navigation_drawer);
        Views.inject(this);

        initDrawerLayout(state);
    }

    @Override protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    private void initDrawerLayout(Bundle state) {
        String[] navigationItems = getResources().getStringArray(R.array.navigation_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navigationItems));
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        title = drawerTitle = getTitle();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        if (state == null) {
            selectItem(0);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        String[] navigationItems = getResources().getStringArray(R.array.navigation_drawer);

        Fragment fragment = null;

        String selectedItem = navigationItems[position];
        if(selectedItem.equals(getString(R.string.home))) {
            fragment = new HomeFragment();
        } else if(selectedItem.equals(getString(R.string.schedule))) {
            fragment = new ScheduleDashboardFragment();
        } else if(selectedItem.equals(getString(R.string.speakers))) {
            fragment = new SpeakerDashboardFragment();
        } else if(selectedItem.equals(getString(R.string.twitter))) {

        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawerList.setItemChecked(position, true);
        setTitle(selectedItem);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }

    protected <T extends Parcelable> T getParcelableFromIntent(String key) {
        return getIntent().getParcelableExtra(key);
    }
}
