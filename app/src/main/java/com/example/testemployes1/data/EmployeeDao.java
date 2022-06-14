package com.example.testemployes1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testemployes1.pojo.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employees")
    LiveData<List<Employee>> getAllEmployees();

    //могут быть одинаковые id и будет краш, что бы избежать onConflict = OnConflictStrategy.REPLACE заменится запись в таблице
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertEmployees(List<Employee> employees);

    @Query("DELETE FROM employees")
    void deleteAllEmployees();
}
