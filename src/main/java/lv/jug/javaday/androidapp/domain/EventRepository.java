package lv.jug.javaday.androidapp.domain;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class EventRepository {

    private static ClassLogger logger = new ClassLogger(EventRepository.class);

    @Inject
    DatabaseHelper db;

    public List<Event> loadByRoom(int id) {
        try {
            Dao<Event, Integer> dao = db.getEventDao();
            QueryBuilder<Event, Integer> builder = dao.queryBuilder();
            builder.where().eq(Event.ROOM_ID_ID, id);
            PreparedQuery<Event> preparedQuery = builder.prepare();

            return dao.query(preparedQuery);
        } catch (SQLException e){
            logger.e(e.getLocalizedMessage());
        }

        return null;
    }

    public List<Event> loadForSpeaker(Speaker speaker) {
        try {
            String speakerName = speaker.getName();
            Dao<Event, Integer> dao = db.getEventDao();
            QueryBuilder<Event, Integer> builder = dao.queryBuilder();
            builder.where().like(Event.SPEAKER_NAME_ID, "%" + speakerName + "%");
            PreparedQuery<Event> preparedQuery = builder.prepare();

            return dao.query(preparedQuery);
        } catch (SQLException e){
            logger.e("Failed to find Events for speaker", e);
        }

        return null;
    }
}
