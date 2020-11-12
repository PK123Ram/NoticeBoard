package com.example.noticeboard.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noticeboard.database.entity.NoticeEntity;
import com.example.noticeboard.databinding.ItemNoticeBinding;
import com.example.noticeboard.utils.callbacks.ItemClickedListener;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private final ItemClickedListener clickListener;
    private List<NoticeEntity> noticeEntities;

    public NoticeAdapter(ItemClickedListener clickListener, List<NoticeEntity> noticeEntities) {
        this.noticeEntities = noticeEntities;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoticeBinding binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoticeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        holder.bind(noticeEntities.get(holder.getAdapterPosition()), this.clickListener);
    }

    @Override
    public int getItemCount() {
        return noticeEntities.size();
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {

        private ItemNoticeBinding binding;

        public NoticeViewHolder(ItemNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final NoticeEntity noticeEntity, final ItemClickedListener clickListener) {
            binding.setNoticeEntity(noticeEntity);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(noticeEntity);
                }
            });
        }
    }

}
