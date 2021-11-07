package com.sudiptacseseu.noteme.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sudiptacseseu.noteme.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    @Query("DELETE FROM note_table WHERE id = :id")
    void deleteANote(int id);

    @Query("UPDATE note_table SET note_name = :noteName, note_description = :noteDescription, note_deadline = :noteDeadline, note_status = :noteStatus WHERE id = :id")
    void updateNoteItem(int id, String noteName, String noteDescription, String noteDeadline, String noteStatus);

    @Query("SELECT * FROM note_table ORDER BY note_deadline DESC")
    LiveData<List<Note>> getAllToDos();

    @Query("SELECT * FROM note_table WHERE note_status = :noteStatus ORDER BY note_deadline DESC")
    LiveData<List<Note>> getAllToDosByStatus(String noteStatus);

}
