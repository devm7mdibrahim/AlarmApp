package com.devmohamedibrahim1997.alarmapp.database;

import android.content.Context;

import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Alarm.class, version = 6, exportSchema = false)
public abstract class AlarmDataBase extends RoomDatabase {

    private static AlarmDataBase instance;
    public abstract AlarmDao alarmDao();

    public static synchronized AlarmDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AlarmDataBase.class,"alarm_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
