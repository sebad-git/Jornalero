package com.sdm.jornalero.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JOBS")
public class Job {

    public final static String JOB_ID = "jobId";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "JB_ID" )
    public Long id;
    @ColumnInfo(name = "JB_NAME" )
    public String name;
    @ColumnInfo(name = "JB_DESCRIPTION" )
    public String description;
    @ColumnInfo(name = "JB_JOB_TYPE" )
    public Integer jobType;
    @ColumnInfo(name = "JB_HOURS" )
    public Integer hours;
    @ColumnInfo(name = "JB_SALARY" )
    public Float salary;
    @ColumnInfo(name = "JB_JOB_LOCATION" )
    public Long jobLocation;
    @ColumnInfo(name = "JB_JOB_LOCATION_ADDRESS" )
    public String address;

}
