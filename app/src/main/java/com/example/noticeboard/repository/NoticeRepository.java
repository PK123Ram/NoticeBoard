package com.example.noticeboard.repository;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.noticeboard.database.NoticeDataBase;
import com.example.noticeboard.database.entity.NoticeEntity;

import java.util.List;

public class NoticeRepository {

    private LiveData<List<NoticeEntity>> noticeEntityLiveData;
    private NoticeDataBase db;

    public NoticeRepository(Context context) {
        db = NoticeDataBase.getInstance(context);
        noticeEntityLiveData = db.noticeDao().getAllNotice();
    }

    /**
     * Function to return live data of all notice.
     *
     * @return
     */
    public LiveData<List<NoticeEntity>> getNoticeLiveData() {
        return noticeEntityLiveData;
    }
}
