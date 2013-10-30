package lv.jug.javaday.androidapp.domain;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class SpeakerRepository {

    private static ClassLogger logger = new ClassLogger(SpeakerRepository.class);

    @Inject
    DatabaseHelper db;

    public List<Speaker> loadAll() {
        try {
            Dao<Speaker, String> dao = db.getSpeakerDao();
            QueryBuilder<Speaker, String> builder = dao.queryBuilder();
            PreparedQuery<Speaker> preparedQuery = builder.prepare();

            return dao.query(preparedQuery);
        } catch (SQLException e){
            logger.e(e.getLocalizedMessage());
        }

        return null;
    }

    public List<Speaker> load(String speakerId) {
        try {
            String[] speakerNames = speakerId.split(Speaker.SPEAKER_DELIMITER);
            Dao<Speaker, String> dao = db.getSpeakerDao();
            QueryBuilder<Speaker, String> builder = dao.queryBuilder();
            builder.where().in(Speaker.NAME_ID, speakerNames);
            PreparedQuery<Speaker> preparedQuery = builder.prepare();

            return dao.query(preparedQuery);
        } catch (SQLException e){
            logger.e(e.getLocalizedMessage());
        }

        return null;
    }
}
