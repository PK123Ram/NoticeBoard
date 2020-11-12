package com.example.noticeboard.utils.callbacks;

import com.example.noticeboard.database.entity.NoticeEntity;

/**
 * Callback to communicate click event from fragment to parent activity
 */
public interface ItemClickedListener {
    void onClick(NoticeEntity noticeEntity);
}
