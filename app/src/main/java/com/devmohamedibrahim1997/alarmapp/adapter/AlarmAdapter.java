package com.devmohamedibrahim1997.alarmapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devmohamedibrahim1997.alarmapp.pojo.Alarm;
import com.devmohamedibrahim1997.alarmapp.R;
import com.devmohamedibrahim1997.alarmapp.databinding.AlarmDataBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private static ClickListener mClickListener;
    private Context mContext;
    private List<Alarm> mAlarms;

    public AlarmAdapter(Context context ){
        mContext = context;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlarmDataBinding alarmDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(alarmDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        holder.bindAlarm(mAlarms.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlarms!= null? mAlarms.size(): 0;
    }

    public void setAlarms(List<Alarm> alarms){
        mAlarms = alarms;
        notifyDataSetChanged();
    }

    public Alarm getAlarmAt(int position){
        return mAlarms.get(position);
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AlarmDataBinding alarmDataBinding;

        AlarmViewHolder(AlarmDataBinding alarmDataBinding) {
            super(alarmDataBinding.getRoot());
            this.alarmDataBinding = alarmDataBinding;
            itemView.setOnClickListener(this);
        }

        void bindAlarm(Alarm alarm) {
            alarmDataBinding.setAlarm(alarm);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onItemClick(getAdapterPosition(),view);
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
    }
}
