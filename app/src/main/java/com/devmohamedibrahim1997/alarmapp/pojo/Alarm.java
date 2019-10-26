package com.devmohamedibrahim1997.alarmapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alarm implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int hour, minute;
    private String label;
    private boolean isActive;

    public Alarm(int hour, int minute, String label, boolean isActive) {
        this.hour = hour;
        this.minute = minute;
        this.label = label;
        this.isActive = isActive;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getLabel() {
        return label;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }



    public String getTime(){
        String dayPart = getHour() / 12 == 1 ? " PM" : " AM";
        return String.format("%02d : %02d", (getHour() % 12),getMinute()) + dayPart;
    }

    protected Alarm(Parcel in) {
        id = in.readInt();
        hour = in.readInt();
        minute = in.readInt();
        label = in.readString();
        isActive = in.readByte() != 0;
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeString(label);
        parcel.writeByte((byte) (isActive ? 1 : 0));
    }
}
