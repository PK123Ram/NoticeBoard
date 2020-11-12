package com.example.noticeboard.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.noticeboard.database.dao.NoticeDaoInterface;
import com.example.noticeboard.database.entity.NoticeEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NoticeEntity.class}, exportSchema = false, version = 3)
public abstract class NoticeDataBase extends RoomDatabase {

    public abstract NoticeDaoInterface noticeDao();

    public static volatile NoticeDataBase INSTANCE = null;

    public static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static NoticeDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NoticeDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, NoticeDataBase.class, "notice_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    NoticeDaoInterface dao = INSTANCE.noticeDao();
                    dao.deleteAll();
                    NoticeEntity noticeEntity = new NoticeEntity();
                    noticeEntity.setTitle("Exam Time Table");
                    noticeEntity.setDate("10-Nov-2020");
                    noticeEntity.setTeacherName("Mrs.Vidyashree");
                    noticeEntity.setDetails("Time table for exam is rescheduled because of the current pandemic condition."
                            + "The exam schedule will further get notified.");
                    dao.insert(noticeEntity);

                    noticeEntity = new NoticeEntity();
                    noticeEntity.setTitle("Winter Holidays");
                    noticeEntity.setTeacherName("Mr.Pradyuman");
                    noticeEntity.setDate("10-Nov-2020");
                    noticeEntity.setDetails("Winter holidays for students will be effective from 15-Oct-2020.");
                    dao.insert(noticeEntity);

                    noticeEntity = new NoticeEntity();
                    noticeEntity.setTitle("Fan-Fest");
                    noticeEntity.setDate("22-Oct-2020");
                    noticeEntity.setTeacherName("Mrs.Geetanjali");
                    noticeEntity.setDetails("The College Fest has been organised and will start from 20-Nov-2020." +
                            "Please follow all the details and do start registration ASAP");
                    dao.insert(noticeEntity);

                    noticeEntity = new NoticeEntity();
                    noticeEntity.setTitle("Best Bulletin Board result");
                    noticeEntity.setTeacherName("Mr.Nandamuri");
                    noticeEntity.setDate("11-Nov-2020");
                    noticeEntity.setDetails("Vidya got 1sp place in best bulletin competition.The bulletin says 'Nothing can dim the light that shines from within'");
                    dao.insert(noticeEntity);

                    noticeEntity = new NoticeEntity();
                    noticeEntity.setTitle("Happy New Year");
                    noticeEntity.setTeacherName("Ms.Rajeshwari");
                    noticeEntity.setDate("1-Jan-2021");
                    noticeEntity.setDetails("You are invited New Year Celebration." +
                            " Please visit our website for more details www.helloworldfanfest.in");
                    dao.insert(noticeEntity);
                }
            });
        }
    };

}
