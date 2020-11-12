package com.example.noticeboard.database.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "noticeBoard_table")
public class NoticeEntity implements Parcelable {

    public static final Creator<NoticeEntity> CREATOR = new Creator<NoticeEntity>() {
        @Override
        public NoticeEntity createFromParcel(Parcel in) {
            return new NoticeEntity(in);
        }

        @Override
        public NoticeEntity[] newArray(int size) {
            return new NoticeEntity[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String title;
    private String teacherName;
    private String details;

    public NoticeEntity() {
    }

    protected NoticeEntity(Parcel in) {
        id = in.readInt();
        date = in.readString();
        title = in.readString();
        details = in.readString();
        teacherName = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeString(details);
        parcel.writeString(teacherName);
    }
}
