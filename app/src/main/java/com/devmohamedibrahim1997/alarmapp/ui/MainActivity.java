package com.devmohamedibrahim1997.alarmapp.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import com.devmohamedibrahim1997.alarmapp.R;
import com.devmohamedibrahim1997.alarmapp.adapter.AlarmAdapter;
import com.devmohamedibrahim1997.alarmapp.databinding.ActivityMainBinding;
import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlarmAdapter alarmAdapter;
    private AlarmViewModel alarmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        alarmAdapter = new AlarmAdapter(this);
        dataBinding.alarmsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.alarmsRecyclerView.setAdapter(alarmAdapter);
        dataBinding.alarmsRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10, 5, 10, 5);
            }
        });

        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        alarmViewModel.getAllAlarms().observe(this, new Observer<List<Alarm>>() {
            @Override
            public void onChanged(List<Alarm> alarms) {
                alarmAdapter.setAlarms(alarms);
            }
        });

        alarmAdapter.setOnItemClickListener(new AlarmAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                //dialog
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                alarmViewModel.deleteAlarm(alarmAdapter.getAlarmAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(dataBinding.alarmsRecyclerView);

        dataBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AlarmActivity.class));
            }
        });
    }
}
