package com.example.noticeboard.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.noticeboard.R;
import com.example.noticeboard.database.entity.NoticeEntity;
import com.example.noticeboard.databinding.NoticeActivityBinding;
import com.example.noticeboard.utils.callbacks.ItemClickedListener;

public class NoticeActivity extends AppCompatActivity implements ItemClickedListener {

    private static final String TAG = NoticeActivity.class.getSimpleName();
    private NoticeActivityBinding binding;
    private boolean isBigScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice);
        isBigScreen = binding.twoPaneLayout != null;
    }

    /**
     * Function to handel click of a notice item
     *
     * @param noticeEntity selected notice
     */
    @Override
    public void onClick(NoticeEntity noticeEntity) {
        if (isBigScreen) {
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setNoticeEntity(noticeEntity);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailsFragment, detailsFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(getString(R.string.notice_parsed), noticeEntity);
            startActivity(intent);
        }
    }
}