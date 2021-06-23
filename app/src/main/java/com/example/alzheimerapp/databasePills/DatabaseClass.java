package com.example.alzheimerapp.databasePills;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.alzheimerapp.databasePills.EntityClass.class}, version = 1)
public abstract class DatabaseClass extends RoomDatabase {
    private static com.example.alzheimerapp.databasePills.DatabaseClass INSTANCE;

    public abstract EventDao EventDao();

    public static com.example.alzheimerapp.databasePills.DatabaseClass getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.alzheimerapp.databasePills.DatabaseClass.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    com.example.alzheimerapp.databasePills.DatabaseClass.class,
                                    "product_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
   // public abstract EventDao getEventDao();
}
