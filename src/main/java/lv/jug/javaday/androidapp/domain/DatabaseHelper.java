package lv.jug.javaday.androidapp.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.StringService;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;

import javax.inject.Inject;
import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static ClassLogger logger = new ClassLogger(DatabaseHelper.class);

    private static final String DATABASE_NAME = "javaday.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Speaker, String> speakerDao;
    private Dao<Event, Integer> eventDao;

    @Inject
    StringService strings;

    @Inject
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            logger.i("onCreate");
            TableUtils.createTable(connectionSource, Speaker.class);
            TableUtils.createTable(connectionSource, Event.class);

            getSpeakerDao();
            speakerDao.create(newSpeaker("simon_ritter"));
            speakerDao.create(newSpeaker("cedric"));
            speakerDao.create(newSpeaker("mirco_dotta"));
            speakerDao.create(newSpeaker("tero"));
            speakerDao.create(newSpeaker("patroklos"));
            speakerDao.create(newSpeaker("kuksenko"));
            speakerDao.create(newSpeaker("sizov"));
            speakerDao.create(newSpeaker("gulati"));
            speakerDao.create(newSpeaker("valenta"));
            speakerDao.create(newSpeaker("jaroslaw_palka"));
            speakerDao.create(newSpeaker("zeeb"));
            speakerDao.create(newSpeaker("fedorov"));
            speakerDao.create(newSpeaker("luciano"));
            speakerDao.create(newSpeaker("antipin"));
            speakerDao.create(newSpeaker("magda"));

            getEventDao();
            eventDao.create(new Event(4, "8:30", "Registration", null, null, null));
            eventDao.create(new Event(4, "9:45", "Conference Opening", null, null, null));
            eventDao.create(new Event(4, "10:00", "Project Lambda: Functional Programming Constructs and Simpler Concurrency in Java SE 8", "description", "Name1", null));

            eventDao.create(new Event(5, "8:30", "Registration", null, null, null));
            eventDao.create(new Event(5, "9:45", "Conference Opening", null, null, null));

            eventDao.create(new Event(6, "8:30", "Registration", null, null, null));
            eventDao.create(new Event(6, "9:45", "Conference Opening", null, null, null));

        } catch (SQLException e) {
            logger.e("Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            logger.i("onUpgrade");
            TableUtils.dropTable(connectionSource, Speaker.class, true);
            TableUtils.dropTable(connectionSource, Event.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            logger.e("Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

     public Dao<Speaker, String> getSpeakerDao() throws SQLException {
        if (speakerDao == null) {
            speakerDao = getDao(Speaker.class);
        }
        return speakerDao;
     }

     public Dao<Event, Integer> getEventDao() throws SQLException {
        if (eventDao == null) {
            eventDao = getDao(Event.class);
        }
        return eventDao;
     }

    private Speaker newSpeaker(String name) {
        // TODO: should change portrait
        return new Speaker(strings.loadString(name + "_name"),
                           strings.loadString(name + "_company"),
                           "portrait",
                           strings.loadString(name + "_description"));
    }
}
