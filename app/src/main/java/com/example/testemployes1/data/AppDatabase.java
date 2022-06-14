package com.example.testemployes1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testemployes1.pojo.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    private static final String DB_NAME = "employees.db";

    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (appDatabase != null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            }
        }
        return appDatabase;
    }

    public abstract EmployeeDao employeeDao();
}
