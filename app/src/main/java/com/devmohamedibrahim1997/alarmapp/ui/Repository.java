package com.devmohamedibrahim1997.alarmapp.ui;

import android.app.Application;
import android.os.AsyncTask;

import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;
import com.devmohamedibrahim1997.alarmapp.database.AlarmDao;
import com.devmohamedibrahim1997.alarmapp.database.AlarmDataBase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {
    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> allAlarms;


    Repository(Application context){
        AlarmDataBase instance = AlarmDataBase.getInstance(context);
        alarmDao = instance.alarmDao();
        allAlarms = alarmDao.getAllAlarms();
    }

    void insertAlarm(Alarm alarm){new InsertAlarmAsyncTask(alarmDao).execute(alarm);}

    void updateAlarm(Alarm alarm){new UpdateAlarmAsyncTask(alarmDao).execute(alarm);}

    void deleteAlarm(Alarm alarm){new DeleteAlarmAsyncTask(alarmDao).execute(alarm);}

    LiveData<List<Alarm>> getAllAlarms() {
        return allAlarms;
    }


    private static class InsertAlarmAsyncTask extends AsyncTask<Alarm,Void,Void> {
        private AlarmDao alarmDao;
        InsertAlarmAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }
        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.insertNote(alarms[0]);
            return null;
        }
    }

    private static class UpdateAlarmAsyncTask extends AsyncTask<Alarm,Void,Void> {
        private AlarmDao alarmDao;
        UpdateAlarmAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }
        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.updateNote(alarms[0]);
            return null;
        }
    }

    private static class DeleteAlarmAsyncTask extends AsyncTask<Alarm,Void,Void> {
        private AlarmDao alarmDao;
        DeleteAlarmAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }
        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.deleteNote(alarms[0]);
            return null;
        }
    }
}
