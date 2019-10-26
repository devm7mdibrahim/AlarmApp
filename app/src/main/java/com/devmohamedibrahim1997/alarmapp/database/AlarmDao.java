package com.devmohamedibrahim1997.alarmapp.database;

import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AlarmDao {

    @Insert
    void insertNote(Alarm alarm);

    @Update
    void updateNote(Alarm alarm);

    @Delete
    void deleteNote(Alarm alarm);

    @Query("SELECT * FROM Alarm ORDER BY id ASC")
    LiveData<List<Alarm>> getAllAlarms();
}
