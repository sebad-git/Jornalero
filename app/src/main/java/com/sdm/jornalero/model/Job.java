package com.sdm.jornalero.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JOBS")
public class Job {
    @PrimaryKey
    @ColumnInfo(name = "ID" )
    private Long id;
    @ColumnInfo(name = "NAME" )
    public String name;
    @ColumnInfo(name = "DESCRIPTION" )
    public String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
