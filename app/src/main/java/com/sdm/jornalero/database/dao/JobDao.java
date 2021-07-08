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

    @Query("SELECT * FROM JOBS WHERE JB_ID = :id")
    Job find(Long id);

    @Insert
    void save(Job job);

    @Update
    void update(Job job);

    @Delete
    void delete(Job job);
}
