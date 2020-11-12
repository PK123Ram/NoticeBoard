package com.example.noticeboard.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noticeboard.database.entity.NoticeEntity;
import com.example.noticeboard.repository.NoticeRepository;

import java.util.List;

public class NoticeViewModel extends AndroidViewModel {

    private NoticeRepository noticeRepository;

    public NoticeViewModel(@NonNull Application application) {
        super(application);
        noticeRepository = new NoticeRepository(application.getApplicationContext());
    }

    /**
     * Method to call getNoticeLiveData() of  NoticeRepository
     * @return
     */
    public LiveData<List<NoticeEntity>> getAllNotice() {
        return noticeRepository.getNoticeLiveData();
    }

}
