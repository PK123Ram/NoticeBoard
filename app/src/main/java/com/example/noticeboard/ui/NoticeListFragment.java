package com.example.noticeboard.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.noticeboard.R;
import com.example.noticeboard.database.entity.NoticeEntity;
import com.example.noticeboard.databinding.NoticeFragmentBinding;
import com.example.noticeboard.utils.callbacks.ItemClickedListener;
import com.example.noticeboard.viewmodel.NoticeViewModel;

import java.util.ArrayList;
import java.util.List;

public class NoticeListFragment extends Fragment {

    private NoticeFragmentBinding binding;
    private NoticeAdapter noticeAdapter;
    private List<NoticeEntity> noticeEntityList = new ArrayList<>();
    NoticeViewModel noticeViewModel;
    ItemClickedListener callback;

    public NoticeListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false);
        noticeViewModel = new ViewModelProvider(this).get(NoticeViewModel.class);
        setupRecyclerView();
        subscribeObservers();
        return binding.getRoot();
    }

    /**
     * Function to subscriber observers to liveData
     */
    private void subscribeObservers() {
        noticeViewModel.getAllNotice().observe(this.getViewLifecycleOwner(), new Observer<List<NoticeEntity>>() {
            @Override
            public void onChanged(List<NoticeEntity> noticeEntities) {
                if (noticeEntities != null) {
                    noticeEntityList.clear();
                    noticeEntityList.addAll(noticeEntities);
                    noticeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * Function to setup recycler view displaying list of notice
     */
    private void setupRecyclerView() {
        noticeAdapter = new NoticeAdapter(new ItemClickedListener() {
            @Override
            public void onClick(NoticeEntity noticeEntity) {
                callback.onClick(noticeEntity);
            }
        }, noticeEntityList);
        binding.rvNoticeBoard.setAdapter(noticeAdapter);
    }

    /**
     * onAttach
     *
     * @param context context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (ItemClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Implement OnItemClickedListener");
        }
    }
}
