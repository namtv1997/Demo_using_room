package com.example.demoroomdatabase.data.dao;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.demoroomdatabase.data.entity.EmployeeEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
@Dao
public interface EmployDao {

    @Insert(onConflict = REPLACE)
    void insertEmploy(EmployeeEntity... employeeEntity);

    @Update
    void updateEmploy(EmployeeEntity employeeEntity);

    @Query("DELETE FROM EmployeeEntity")
    void deleteAll();

    @Query("SELECT * FROM EmployeeEntity")
     List<EmployeeEntity> findAllEmploySync();

    @Query("SELECT * FROM EmployeeEntity WHERE employId=:id ")
    EmployeeEntity findEmployById(int id);

    @Delete
    void deleteEmploy(EmployeeEntity employeeEntity);

}
