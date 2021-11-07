package com.sudiptacseseu.noteme.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sudiptacseseu.noteme.dao.NoteDao;
import com.sudiptacseseu.noteme.database.NoteRoomDatabaseInitializer;
import com.sudiptacseseu.noteme.model.Note;

import java.util.List;

public class NoteDatabaseCallRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allToDos;

    public NoteDatabaseCallRepository(Application application) {
        //Get data from a remote API and then put it on a diff. list
        NoteRoomDatabaseInitializer db = NoteRoomDatabaseInitializer.getDatabase(application);
      noteDao = db.noteDao();
      allToDos = noteDao.getAllToDos();
    }

    public LiveData<List<Note>> getAllToDos() {
        return allToDos;
    }

    public void insert(Note note){
         new InsertAsyncTask(noteDao).execute(note);
    }

    public void deleteANote(Note note){
        new DeleteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao asyncTaskDao;
        public InsertAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... params) {
            //[obj1, obj2....]
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao asyncTaskDao;
        public UpdateAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... params) {
            //[obj1, obj2....]
            Note currentNote = params[0];
            asyncTaskDao.updateNoteItem(currentNote.getId()+1, currentNote.getNoteName(),
                    currentNote.getNoteDescription(), currentNote.getNoteDeadline(), currentNote.getNoteStatus());
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao asyncTaskDao;
        public DeleteAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... params) {
            //[obj1, obj2....]
            Note currentNote = params[0];
            asyncTaskDao.deleteANote(currentNote.getId());
            return null;
        }
    }
}
