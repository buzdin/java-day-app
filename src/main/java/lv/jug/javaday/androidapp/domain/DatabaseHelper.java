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

import javax.inject.Inject;
import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "javaday.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Speaker, String> speakerDao;

    @Inject
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Speaker.class);

            speakerDao.create(new Speaker("Name1", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name2", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name3", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name4", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name5", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name6", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name7", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name8", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name9", "Company", "portrait", "Info"));
            speakerDao.create(new Speaker("Name10", "Company", "portrait", "Info"));

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Speaker.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

     public Dao<Speaker, String> getSpeakerDao() throws SQLException {
        if (speakerDao == null) {
            speakerDao = getDao(Speaker.class);
        }
        return speakerDao;
     }
}
