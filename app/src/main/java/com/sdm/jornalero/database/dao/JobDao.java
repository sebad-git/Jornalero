package com.sdm.jornalero.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.sdm.jornalero.model.Job;
import java.util.List;

@Dao
public interface JobDao {

    @Query("SELECT * FROM JOBS")
    List<Job> list();

    @Query("SELECT * FROM JOBS WHERE ID = :id")
    Job find(Long id);

    @Insert
    void save(Job location);

    @Update
    void update(Job location);

    @Delete
    void delete(Job location);
}
