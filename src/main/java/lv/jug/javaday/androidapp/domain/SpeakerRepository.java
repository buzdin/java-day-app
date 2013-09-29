package lv.jug.javaday.androidapp.domain;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class SpeakerRepository {

    @Inject
    DatabaseHelper db;

    public List<Speaker> loadAll() {
        try {
            Dao<Speaker, String> dao = db.getSpeakerDao();
            QueryBuilder<Speaker, String> builder = dao.queryBuilder();
            PreparedQuery<Speaker> preparedQuery = builder.prepare();

            return dao.query(preparedQuery);
        } catch (SQLException e){
            Log.e(SpeakerRepository.class.getName(), e.getLocalizedMessage());
        }

        return null;
    }

}
