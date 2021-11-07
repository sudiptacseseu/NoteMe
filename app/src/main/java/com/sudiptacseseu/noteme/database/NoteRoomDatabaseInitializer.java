package com.sudiptacseseu.noteme.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sudiptacseseu.noteme.dao.NoteDao;
import com.sudiptacseseu.noteme.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteRoomDatabaseInitializer extends RoomDatabase {

    public static volatile NoteRoomDatabaseInitializer INSTANCE;
    public abstract NoteDao noteDao();

    public static NoteRoomDatabaseInitializer getDatabase(final Context context) {
        if (INSTANCE == null) {
             synchronized (NoteRoomDatabaseInitializer.class) {
                  if (INSTANCE == null) {
                      //create our db
                      INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                              NoteRoomDatabaseInitializer.class, DatabaseConstants.NOTE_DATABASE)
                              .addCallback(roomDatabaseCallBack)
                              .build();
                  }
             }
        }
        return INSTANCE;
    }

    private static Callback roomDatabaseCallBack =
                new Callback() {
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        new PopulateDbAsync(INSTANCE).execute();
                    }
                };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final NoteDao noteDao;

        public PopulateDbAsync(NoteRoomDatabaseInitializer db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
