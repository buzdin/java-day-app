package lv.jug.javaday.androidapp.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lv.jug.javaday.androidapp.common.StringService;
import lv.jug.javaday.androidapp.common.StringUtils;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lv.jug.javaday.androidapp.domain.CountryCodes.*;
import static lv.jug.javaday.androidapp.domain.IconCodes.*;
import static lv.jug.javaday.androidapp.domain.Speaker.*;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static ClassLogger logger = new ClassLogger(DatabaseHelper.class);

    private static final String DATABASE_NAME = "javaday.db";
    private static final int DATABASE_VERSION = 23;
	private static final int NOT_MAPPED = -1;

    private Dao<Speaker, String> speakerDao;
    private Dao<Event, Integer> eventDao;

    @Inject
    StringService stringService;

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
            speakerDao.create(newSpeaker(CEDRIC_CHAMPEAU, FRANCE));
            speakerDao.create(newSpeaker(MIRCO_DOTTA, SWISS));
            speakerDao.create(newSpeaker(TERO_PARVIAINEN, FINLAND));
            speakerDao.create(newSpeaker(PATROKLOS_PAPAPERROU, GREECE));
            speakerDao.create(newSpeaker(SERGEY_KUKSENKO, RUSSIA));
            speakerDao.create(newSpeaker(EDUARD_SIZOV, LATVIA));
            speakerDao.create(newSpeaker(JAN_VALENTA, CZECH));
            speakerDao.create(newSpeaker(NICK_ZEEB, UK));
            speakerDao.create(newSpeaker(ALEXEY_FEDOROV, RUSSIA));
            speakerDao.create(newSpeaker(LUCIANO_FIANDESIO, ITALY));
            speakerDao.create(newSpeaker(ROMAN_ANTIPIN, RUSSIA));
            speakerDao.create(newSpeaker(ALEXANDER_MIRONENKO, RUSSIA));
            speakerDao.create(newSpeaker(ANDREY_ADAMOVICH, LATVIA));
            speakerDao.create(newSpeaker(DIRK_MAHLER, GERMANY));
            speakerDao.create(newSpeaker(ALEXEY_SHEVCHUK, LATVIA));

            getEventDao();
            // Room 4
            eventDao.create(newEvent(4, "8:30", "Registration", null));
            eventDao.create(newEvent(4, "9:45", "Conference Opening", null));
            eventDao.create(newEvent(4, "10:00", SIMON_RITTER));
            eventDao.create(newEvent(4, "11:00", "Coffee Pause", COFFEE_ICON));
            eventDao.create(newEvent(4, "11:30", TERO_PARVIAINEN));
            eventDao.create(newEvent(4, "12:30", "Lunch", LUNCH_ICON));
            eventDao.create(newEvent(4, "13:30", MIRCO_DOTTA));
            eventDao.create(newEvent(4, "14:30", CEDRIC_CHAMPEAU));
            eventDao.create(newEvent(4, "15:30", "Coffee Pause", COFFEE_ICON));
            eventDao.create(newEvent(4, "16:00", SERGEY_KUKSENKO, 1));
            eventDao.create(newEvent(4, "17:00", NICK_ZEEB));
            eventDao.create(newEvent(4, "17:50", "Conference Closing", null));
            eventDao.create(newEvent(4, "18:00", "The End", null));
            eventDao.create(newEvent(4, "19:00", "Afterparty", null));

            // Room 5
            eventDao.create(newEvent(5, "8:30", "Registration", null));
            eventDao.create(newEvent(5, "9:45", "Conference Opening", null));
            eventDao.create(newEvent(5, "10:00", SIMON_RITTER));
            eventDao.create(newEvent(5, "11:00", "Coffee Pause", COFFEE_ICON));
            eventDao.create(newEvent(5, "11:30", DIRK_MAHLER));
            eventDao.create(newEvent(5, "12:30", "Lunch", LUNCH_ICON));
            eventDao.create(newEvent(5, "13:30", SERGEY_KUKSENKO, 2));
            eventDao.create(newEvent(5, "14:30", PATROKLOS_PAPAPERROU));
            eventDao.create(newEvent(5, "15:30", "Coffee Pause", COFFEE_ICON));
            eventDao.create(newEvent(5, "16:00", true, LUCIANO_FIANDESIO, ANDREY_ADAMOVICH));
            eventDao.create(newEvent(5, "17:00", EDUARD_SIZOV));
            eventDao.create(newEvent(5, "17:50", "Conference Closing", null));
            eventDao.create(newEvent(5, "18:00", "The End", null));
            eventDao.create(newEvent(5, "19:00", "Afterparty", null));

            // Room 6
            eventDao.create(newEvent(6, "8:30", "Registration", null));
            eventDao.create(newEvent(6, "9:45", "Conference Opening", null));
            eventDao.create(newEvent(6, "10:00", "Room 4", LEFT_ARROW_ICON));
            eventDao.create(newEvent(6, "11:00", "Coffee Pause", COFFEE_ICON));
            eventDao.create(newEvent(6, "11:30", ALEXEY_FEDOROV));
            eventDao.create(newEvent(6, "12:30", "Lunch", LUNCH_ICON));
            eventDao.create(newEvent(6, "13:30", ROMAN_ANTIPIN));
            eventDao.create(newEvent(6, "14:30", ALEXANDER_MIRONENKO));
            eventDao.create(newEvent(6, "15:30", "Coffee Pause", COFFEE_ICON));
            eventDao.create(newEvent(6, "16:00", JAN_VALENTA));
            eventDao.create(newEvent(6, "17:00", ALEXEY_SHEVCHUK));
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
        return new Speaker(stringService.loadString(name + "_name"),
                           stringService.loadString(name + "_company"),
                           name,
                           stringService.loadString(name + "_description"),
                           country,
                           stringService.loadString(name + "_twitter"));
    }

    private Event newEvent(int roomId, String time, String title, String speakerName, String icon) {
        return newEvent(roomId, time, title, speakerName, icon, null);
    }

    private Event newEvent(int roomId, String time, String title, String speakerName, String icon, Integer presentationNumber) {
        String description = null;
        int sessionId = NOT_MAPPED;
        if (speakerName != null) {
            String presentationTitle = speakerName + "_presentation_title";
            String presentationDescription = speakerName + "_presentation_description";
            if(presentationNumber != null) {
                presentationTitle +=  presentationNumber;
                presentationDescription +=  presentationNumber;
            }
            title = stringService.loadString(presentationTitle);
            description = stringService.loadString(presentationDescription);
            speakerName = stringService.loadString(speakerName + "_name");
            String session = stringService.safeLoadString(presentationTitle + "_session_id");
            if (session != null) sessionId = new Integer(session);
        }
        return new Event(sessionId, roomId,
                         time,
                         title,
                         description,
                         speakerName,
                         icon);
    }

    private Event newEvent(int roomId, String time, String speakerName, int presentationNumber) {
        return newEvent(roomId, time, null, speakerName, null, presentationNumber);
    }

    private Event newEvent(int roomId, String time, String speakerName) {
        return newEvent(roomId, time, null, speakerName, null);
    }

    private Event newEvent(int roomId, String time, String title, String icon) {
        return newEvent(roomId, time, title, null, icon);
    }

    private Event newEvent(int roomId, String time, boolean multipleSpeakers, String ... speakerNames) {
        Event event = newEvent(roomId, time, null, speakerNames[0], null);
        List<String> names = new ArrayList<String>();
        for (String speakerName : speakerNames) {
            names.add(speakerName + "_name");
        }
        List<String> strings = stringService.loadStrings(names);
        event.setSpeakerName(StringUtils.join(strings, SPEAKER_DELIMITER));
        return event;
    }
}
