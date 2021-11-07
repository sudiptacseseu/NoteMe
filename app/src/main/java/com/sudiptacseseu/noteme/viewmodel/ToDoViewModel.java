package com.sudiptacseseu.noteme.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sudiptacseseu.noteme.model.Note;
import com.sudiptacseseu.noteme.repository.NoteDatabaseCallRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {
    private NoteDatabaseCallRepository noteDatabaseCallRepository;
    private LiveData<List<Note>> allToDos;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        noteDatabaseCallRepository = new NoteDatabaseCallRepository(application);
        allToDos = noteDatabaseCallRepository.getAllToDos();
    }

    public LiveData<List<Note>> getAllToDos() {
        return allToDos;
    }

    public LiveData<List<Note>> getToDosByStatus(String noteStatus) {
        return noteDatabaseCallRepository.getAllToDosByStatus(noteStatus);
    }

    public void insert(Note note) {
        noteDatabaseCallRepository.insert(note);
    }

    public void update(Note note) {
        noteDatabaseCallRepository.update(note);
    }

    public void deleteANote(Note note) {
        noteDatabaseCallRepository.deleteANote(note);
    }
}
