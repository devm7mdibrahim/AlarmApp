package com.devmohamedibrahim1997.alarmapp.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.devmohamedibrahim1997.alarmapp.AlarmBroadcastReceiver;
import com.devmohamedibrahim1997.alarmapp.R;
import com.devmohamedibrahim1997.alarmapp.databinding.ActivityAlarmBinding;
import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class AlarmActivity extends AppCompatActivity {

    private static AlarmManager alarmManager;
    private ActivityAlarmBinding alarmDataBinding;
    private AlarmViewModel alarmViewModel;
    private int id;
    private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_alarm);
        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmDataBinding.alarmActivitySetAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmDataBinding.alarmActivityTimePicker.getHour());
                calendar.set(Calendar.MINUTE, alarmDataBinding.alarmActivityTimePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                long time = calendar.getTimeInMillis();
                int mTime = (int) (time/60000);

                alarm = new Alarm(alarmDataBinding.alarmActivityTimePicker.getHour(),
                        alarmDataBinding.alarmActivityTimePicker.getMinute(),
                        alarmDataBinding.alarmActivityLabelEditText.getText().toString(),
                        true);
                alarmViewModel.insertAlarm(alarm);


                if(alarmViewModel.getAllAlarms().getValue()!= null) {
                    int size = alarmViewModel.getAllAlarms().getValue().size();
                    id = alarmViewModel.getAllAlarms().getValue().get(size).getId();
                }
                setAlarm(getApplicationContext(),id,calendar);
                finish();
            }
        });

        alarmDataBinding.alarmActivityCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setAlarm(Context context, int id, Calendar calendar) {
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra("alarmId",alarm.getId());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

//    public static void cancelAlarm(Context context, int id){
//        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent,0);
//        alarmManager.cancel(pendingIntent);
//    }

}
