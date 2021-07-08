package com.sdm.jornalero.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.sdm.jornalero.database.dao.JobDao;
import com.sdm.jornalero.model.Job;

@Database(entities = {Job.class}, version = 1, exportSchema = false )
public abstract class JornalerosDatabase extends RoomDatabase {

    private static JornalerosDatabase database;

    public static JornalerosDatabase getDatabase(Context context){
        if(database==null){
            database=Room.databaseBuilder(context, JornalerosDatabase.class, "Jornaleros.db")
                    .allowMainThreadQueries().build();
        }
        return database;
    }

    public abstract JobDao jobsDao();

}
