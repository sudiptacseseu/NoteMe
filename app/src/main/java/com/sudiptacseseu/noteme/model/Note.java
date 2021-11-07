package com.sudiptacseseu.noteme.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.sudiptacseseu.noteme.database.DatabaseConstants;

import java.io.Serializable;

@Entity(tableName = DatabaseConstants.NOTE_TABLE)
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_NAME)
    private String noteName;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_DESCRIPTION)
    private String noteDescription;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_CREATED)
    private String noteCreated;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_DEADLINE)
    private String noteDeadline;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_STATUS)
    private String noteStatus;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_EMAIL)
    private String noteEmail;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_PHONE)
    private String notePhone;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.NOTE_URL)
    private String noteUrl;


    public Note(@NonNull String noteName, @NonNull String noteDescription, @NonNull String noteCreated, @NonNull String noteDeadline,
                @NonNull String noteStatus, @NonNull String noteEmail, @NonNull String notePhone, @NonNull String noteUrl) {
        this.noteName = noteName;
        this.noteDescription = noteDescription;
        this.noteCreated = noteCreated;
        this.noteDeadline = noteDeadline;
        this.noteStatus = noteStatus;
        this.noteEmail = noteEmail;
        this.notePhone = notePhone;
        this.noteUrl = noteUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(@NonNull String noteName) {
        this.noteName = noteName;
    }

    @NonNull
    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(@NonNull String noteDescription) {
        this.noteDescription = noteDescription;
    }

    @NonNull
    public String getNoteCreated() {
        return noteCreated;
    }

    public void setNoteCreated(@NonNull String noteCreated) {
        this.noteCreated = noteCreated;
    }

    @NonNull
    public String getNoteDeadline() {
        return noteDeadline;
    }

    public void setNoteDeadline(@NonNull String noteDeadline) {
        this.noteDeadline = noteDeadline;
    }

    @NonNull
    public String getNoteStatus() {
        return noteStatus;
    }

    public void setNoteStatus(@NonNull String noteStatus) {
        this.noteStatus = noteStatus;
    }

    @NonNull
    public String getNoteEmail() {
        return noteEmail;
    }

    public void setNoteEmail(@NonNull String noteEmail) {
        this.noteEmail = noteEmail;
    }

    @NonNull
    public String getNotePhone() {
        return notePhone;
    }

    public void setNotePhone(@NonNull String notePhone) {
        this.notePhone = notePhone;
    }

    @NonNull
    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(@NonNull String noteUrl) {
        this.noteUrl = noteUrl;
    }

    //    public String getToDo() {
//        return toDo;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setToDo(@NonNull String toDo) {
//        this.toDo = toDo;
//    }
}
