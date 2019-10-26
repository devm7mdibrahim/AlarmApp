package com.devmohamedibrahim1997.alarmapp.ui;

import android.app.Application;

import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AlarmViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Alarm>> allAlarms;

    public AlarmViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        allAlarms = repository.getAllAlarms();
    }

    public void insertAlarm(Alarm alarm){repository.insertAlarm(alarm);}

    public void updateAlarm(Alarm alarm){repository.updateAlarm(alarm);}

    public void deleteAlarm(Alarm alarm){repository.deleteAlarm(alarm);}

    public LiveData<List<Alarm>> getAllAlarms() { return allAlarms;}
}
