package com.example.demoroomdatabase.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.demoroomdatabase.data.converter.TimestampConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(indices = {@Index(value = {"employ_name","employ_adrress"}, unique = true)})
public class EmployeeEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employId")
    public int employId;

    @ColumnInfo(name = "employ_name")
    public String name;

    @ColumnInfo(name = "employ_adrress")
    public String adrress;

    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getEmployId() {
        return employId;
    }

    public void setEmployId(int employId) {
        this.employId = employId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }

}
