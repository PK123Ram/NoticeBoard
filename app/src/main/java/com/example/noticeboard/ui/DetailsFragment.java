package com.example.noticeboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.noticeboard.R;
import com.example.noticeboard.database.entity.NoticeEntity;
import com.example.noticeboard.databinding.DetailsBinding;

public class DetailsFragment extends Fragment {

    private DetailsBinding binding;
    private NoticeEntity noticeEntity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            noticeEntity = savedInstanceState.getParcelable(getString(R.string.notice_parsed));
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        binding.setNoticeEntity(noticeEntity);

        return binding.getRoot();

    }

    /**
     * Function to set notice object
     *
     * @param noticeEntity notice object
     */
    public void setNoticeEntity(NoticeEntity noticeEntity) {
        this.noticeEntity = noticeEntity;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(getString(R.string.notice_parsed), noticeEntity);
    }
}
