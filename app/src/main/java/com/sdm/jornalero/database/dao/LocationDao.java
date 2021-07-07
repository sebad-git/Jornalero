package com.sdm.jornalero.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.sdm.jornalero.model.PictureLocation;


@Dao
public interface LocationDao {

    @Query("SELECT * FROM PICTURE_LOCATIONS")
    List<PictureLocation> list();

    @Query("SELECT * FROM PICTURE_LOCATIONS WHERE LATITUDE = :latitude AND LONGITUDE = :longitude")
    PictureLocation buscar(double latitude, double longitude);

    @Insert
    void save(PictureLocation location);

    @Update
    void update(PictureLocation location);

    @Delete
    void delete(PictureLocation location);
}
