package com.richieoscar.orangenews.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.SavedArticle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Article.class, SavedArticle.class}, version = 3, exportSchema = false)
public abstract class OrangeNewsDatabase extends RoomDatabase {
    public static ExecutorService doInBackground = Executors.newFixedThreadPool(1);
    private static OrangeNewsDatabase INSTANCE;

    public static OrangeNewsDatabase getOrangeNewsDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (OrangeNewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), OrangeNewsDatabase.class,
                            "orangeNewsDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract OrangeDao orangeDao();

}
