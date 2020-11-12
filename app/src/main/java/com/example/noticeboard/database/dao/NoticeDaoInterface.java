package com.example.noticeboard.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.noticeboard.database.entity.NoticeEntity;

import java.util.List;

@Dao
public interface NoticeDaoInterface {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NoticeEntity notices);

    @Query("select * from noticeBoard_table")
    LiveData<List<NoticeEntity>> getAllNotice();

    @Query("delete from noticeBoard_table")
    void deleteAll();

}
