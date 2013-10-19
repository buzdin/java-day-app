package lv.jug.javaday.androidapp.domain;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class EventRepository {

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
            Log.e(EventRepository.class.getName(), e.getLocalizedMessage());
        }

        return null;
    }

}
