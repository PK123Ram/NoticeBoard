package com.example.noticeboard.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.example.noticeboard.R;
import com.example.noticeboard.database.entity.NoticeEntity;
import com.example.noticeboard.databinding.DetailsActivityBinding;

public class DetailsActivity extends AppCompatActivity {

    private DetailsActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        NoticeEntity noticeEntity = getIntent().getParcelableExtra("NOTICE_PARSE");
        if (savedInstanceState == null) {
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setNoticeEntity(noticeEntity);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.detailsFragment, detailsFragment)
                    .commit();

        }
    }
}
