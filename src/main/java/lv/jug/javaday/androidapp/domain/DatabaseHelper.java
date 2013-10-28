package lv.jug.javaday.androidapp.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lv.jug.javaday.androidapp.application.StringService;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;

import javax.inject.Inject;
import java.sql.SQLException;

import static lv.jug.javaday.androidapp.domain.Speaker.*;
import static lv.jug.javaday.androidapp.domain.CountryCodes.*;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static ClassLogger logger = new ClassLogger(DatabaseHelper.class);

    private static final String DATABASE_NAME = "javaday.db";
    private static final int DATABASE_VERSION = 2;

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
            speakerDao.create(newSpeaker(SIMON_RITTER, UK));
            speakerDao.create(newSpeaker(CEDRIC_CHAMPEAU, FR));
            speakerDao.create(newSpeaker(MIRCO_DOTTA, CH));
            speakerDao.create(newSpeaker(TERO_PARVIAINEN, FI));
            speakerDao.create(newSpeaker(PATROKLOS_PAPAPERROU, GR));
            speakerDao.create(newSpeaker(SERGEY_KUKSENKO, RU));
            speakerDao.create(newSpeaker(EDUARD_SIZOV, LV));
            speakerDao.create(newSpeaker(SHEKHAR_GULATI, IN));
            speakerDao.create(newSpeaker(JAN_VALENTA, CZ));
            speakerDao.create(newSpeaker(JAROSLAW_PALKA, PL));
            speakerDao.create(newSpeaker(NICK_ZEEB, UK));
            speakerDao.create(newSpeaker(ALEXEY_FEDOROV, RU));
            speakerDao.create(newSpeaker(LUCIANO_FIANDESIQ, IT));
            speakerDao.create(newSpeaker(ROMAN_ANTIPIN, RU));
            speakerDao.create(newSpeaker(DENIS_MAGDA, RU));
            // TODO: Deal with multiple speakers for presentation
//            speakerDao.create(newSpeaker(ANDREY_ADAMOVICH, LV));

            getEventDao();
            // Room 4
            eventDao.create(newEvent(4, "8:30", "Registration", null));
            eventDao.create(newEvent(4, "9:45", "Conference Opening", null));
            eventDao.create(newEvent(4, "10:00", SIMON_RITTER));
            eventDao.create(newEvent(4, "11:00", "Coffee Pause", null));
            eventDao.create(newEvent(4, "11:30", TERO_PARVIAINEN));
            eventDao.create(newEvent(4, "12:30", "Lunch", null));
            eventDao.create(newEvent(4, "13:30", MIRCO_DOTTA));
            eventDao.create(newEvent(4, "14:30", NICK_ZEEB));
            eventDao.create(newEvent(4, "15:30", "Coffee Pause", null));
            eventDao.create(newEvent(4, "16:00", SERGEY_KUKSENKO));
            eventDao.create(newEvent(4, "17:00", DENIS_MAGDA));
            eventDao.create(newEvent(4, "17:50", "Conference Closing", null));
            eventDao.create(newEvent(4, "18:00", "The End", null));
            eventDao.create(newEvent(4, "19:00", "Afterparty", null));

            // Room 5
            eventDao.create(newEvent(5, "8:30", "Registration", null));
            eventDao.create(newEvent(5, "9:45", "Conference Opening", null));
            eventDao.create(newEvent(5, "10:00", "Room 4", null));
            eventDao.create(newEvent(5, "11:00", "Coffee Pause", null));
            eventDao.create(newEvent(5, "11:30", ALEXEY_FEDOROV));
            eventDao.create(newEvent(5, "12:30", "Lunch", null));
            eventDao.create(newEvent(5, "13:30", EDUARD_SIZOV));
            eventDao.create(newEvent(5, "14:30", JAN_VALENTA));
            eventDao.create(newEvent(5, "15:30", "Coffee Pause", null));
            eventDao.create(newEvent(5, "16:00", SHEKHAR_GULATI));
            eventDao.create(newEvent(5, "17:00", CEDRIC_CHAMPEAU));
            eventDao.create(newEvent(5, "17:50", "Conference Closing", null));
            eventDao.create(newEvent(5, "18:00", "The End", null));
            eventDao.create(newEvent(5, "19:00", "Afterparty", null));

            // Room 6
            eventDao.create(newEvent(6, "8:30", "Registration", null));
            eventDao.create(newEvent(6, "9:45", "Conference Opening", null));
            eventDao.create(newEvent(6, "10:00", "Room 4", null));
            eventDao.create(newEvent(6, "11:00", "Coffee Pause", null));
            eventDao.create(newEvent(6, "11:30", LUCIANO_FIANDESIQ));
            eventDao.create(newEvent(6, "12:30", "Lunch", null));
            eventDao.create(newEvent(6, "13:30", PATROKLOS_PAPAPERROU));
            eventDao.create(newEvent(6, "14:30", JAROSLAW_PALKA));
            eventDao.create(newEvent(6, "15:30", "Coffee Pause", null));
            eventDao.create(newEvent(6, "16:00", ROMAN_ANTIPIN));
            eventDao.create(newEvent(6, "17:00", "Room 4", null));
            eventDao.create(newEvent(6, "17:50", "Conference Closing", null));
            eventDao.create(newEvent(6, "18:00", "The End", null));
            eventDao.create(newEvent(6, "19:00", "Afterparty", null));


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

    private Speaker newSpeaker(String name, String country) {
        // TODO: should change portrait
        return new Speaker(strings.loadString(name + "_name"),
                           strings.loadString(name + "_company"),
                           "portrait",
                           strings.loadString(name + "_description"),
                           country);
    }

    private Event newEvent(int roomId, String time, String title, String speakerName, String icon) {
        String description = null;
        if (speakerName != null) {
            title = strings.loadString(speakerName + "_presentation_title");
            description = strings.loadString(speakerName + "_presentation_description");
            speakerName = strings.loadString(speakerName + "_name");
        }
        return new Event(roomId,
                         time,
                         title,
                         description,
                         speakerName,
                         icon);
    }

    private Event newEvent(int roomId, String time, String title, String icon) {
        return newEvent(roomId, time, title, null, icon);
    }

    private Event newEvent(int roomId, String time, String speakerName) {
        return newEvent(roomId, time, null, speakerName, null);
    }
}
