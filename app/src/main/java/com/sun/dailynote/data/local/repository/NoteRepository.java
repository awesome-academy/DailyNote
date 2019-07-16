package com.sun.dailynote.data.local.repository;

import com.sun.dailynote.data.NoteDataSoucre;
import com.sun.dailynote.data.model.Note;
import com.sun.dailynote.ui.BaseCallBack;

import java.sql.Date;
import java.util.List;

public class NoteRepository implements NoteDataSoucre.Local {

    private NoteDataSoucre mNoteDataSoucre;

    public NoteRepository(NoteDataSoucre noteDataSoucre) {
        mNoteDataSoucre = noteDataSoucre;
    }

    @Override
    public void getNotes(Date date, BaseCallBack<List<Note>> callBack) {

    }

    @Override
    public void getNote(int id, BaseCallBack<Note> callback) {

    }

    @Override
    public void getNotes(int status, BaseCallBack<List<Note>> callBack) {

    }

    @Override
    public void insertNote(Note note, BaseCallBack callBack) {

    }

    @Override
    public void updateNote(Note note, BaseCallBack callBack) {

    }

    @Override
    public void deleteNote(Note note, BaseCallBack callBack) {

    }
}
