package com.sun.dailynote.data;

import com.sun.dailynote.data.model.Note;
import com.sun.dailynote.ui.BaseCallBack;
import java.sql.Date;
import java.util.List;

public interface NoteDataSoucre {
    interface Local{
        void getNotes(Date date, BaseCallBack<List<Note>> callBack);
        void getNote(int id, BaseCallBack<Note> callback);
        void getNotes(int status, BaseCallBack<List<Note>> callBack);
        void insertNote(Note note, BaseCallBack callBack);
        void updateNote(Note note, BaseCallBack callBack);
        void deleteNote(Note note, BaseCallBack callBack);
    }
    interface Remote{

    }
}
