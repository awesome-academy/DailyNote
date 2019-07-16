package com.sun.dailynote.data.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.sun.dailynote.R;
import com.sun.dailynote.data.NoteDataSoucre;
import com.sun.dailynote.data.model.Note;
import com.sun.dailynote.ui.BaseCallBack;
import com.sun.dailynote.utils.Constants;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class NoteLocalDatasource implements NoteDataSoucre.Local {
    private NoteDatabase mNoteDatabase;
    Context context;

    public NoteLocalDatasource(Context context) {
        this.context = context;
        mNoteDatabase = NoteDatabase.getInstance(context,
                Constants.DATABASE_NAME,
                null,
                Constants.DATABASE_VERSION);
    }

    @Override
    public void getNotes(Date date, BaseCallBack<List<Note>> callBack) {
        List<Note> list = new ArrayList<>();
        Cursor cursor = mNoteDatabase.noteQueryDataWithResult(null,
                NoteDatabase.NoteEntry.NOTE_CREATE_DAY + " = ? ",
                new String[]{String.valueOf(date)},
                null, null, null, null);
        if (convertCusorToList(cursor) != null)
        {
            callBack.onSuccess(convertCusorToList(cursor));
        }
        else {
            callBack.onSuccess(null);
            callBack.onMess(context.getString(R.string.not_found));
        }
    }

    @Override
    public void getNote(int id, BaseCallBack<Note> callback) {
        Cursor cursor = mNoteDatabase.noteQueryDataWithResult(null,
                NoteDatabase.NoteEntry.NOTE_ID + " = ?",
                new String[]{id + ""}, null, null,
                null, null);
        if (convertCusorToList(cursor) != null) {
            callback.onSuccess(convertCusorToList(cursor).get(0));
        } else {
            callback.onSuccess(null);
            callback.onMess(context.getString(R.string.id_not_found));
        }
    }

    @Override
    public void getNotes(int status, BaseCallBack<List<Note>> callBack) {
        Cursor cursor = mNoteDatabase.noteQueryDataWithResult(null,
                NoteDatabase.NoteEntry.NOTE_STATUS + " = ?",
                new String[]{status + ""},
                null, null, null, null);
        if (convertCusorToList(cursor) != null)
        {
            callBack.onSuccess(convertCusorToList(cursor));
        }
        else {
            callBack.onSuccess(null);
            callBack.onMess(context.getString(R.string.not_found));
        }
    }

    @Override
    public void insertNote(Note note, BaseCallBack callBack) {

        long check = mNoteDatabase.noteInsert(NoteDatabase.NoteEntry.NOTE_TABLE_NAME,
                null,
                getContentValues(note));
        if (check != -1) {
            if (check > 0) {
                callBack.onMess(context.getString(R.string.insert_success));
            } else
                callBack.onMess(context.getString(R.string.insert_fail));
        }
    }

    @Override
    public void updateNote(Note note, BaseCallBack callBack) {
        int check = mNoteDatabase.noteUpdate(NoteDatabase.NoteEntry.NOTE_TABLE_NAME, getContentValues(note),
                NoteDatabase.NoteEntry.NOTE_ID + " = ?", String.valueOf(note.getId()));
        if (check > 0) {
            callBack.onSuccess(null);
            callBack.onMess(context.getString(R.string.update_success));
        } else
            callBack.onMess(context.getString(R.string.update_fail));
    }

    @Override
    public void deleteNote(Note note, BaseCallBack callBack) {
        int check = mNoteDatabase.noteDelete(NoteDatabase.NoteEntry.NOTE_TABLE_NAME,
                NoteDatabase.NoteEntry.NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        if (check > 0) {
            callBack.onSuccess(null);
            callBack.onMess(context.getString(R.string.delete_success));
        } else
            callBack.onMess(context.getString(R.string.delete_fail));
    }

    private ContentValues getContentValues(Note note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabase.NoteEntry.NOTE_NAME, note.getName());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_CATEGORY, note.getCategory());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_CONTENT, note.getContent());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_IMAGE, note.getImage());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_STATUS, note.getStatus());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_TIME_START, note.getTimeStart().getTime());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_TIME_END, note.getTimeEnd().getTime());
        contentValues.put(NoteDatabase.NoteEntry.NOTE_CREATE_DAY, note.getDateCreate().getTime());
        return contentValues;
    }

    private List<Note> convertCusorToList(Cursor cursor) {
        List<Note> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.moveToNext()) {
                Note note = new Note.NoteBuilder()
                        .setId(cursor.getInt(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_ID)))
                        .setCategory(cursor.getInt(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_CATEGORY)))
                        .setContent(cursor.getString(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_CONTENT)))
                        .setDateCreate(new Date(cursor.getLong(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_CREATE_DAY))))
                        .setImage(cursor.getString(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_IMAGE)))
                        .setName(cursor.getString(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_NAME)))
                        .setStatus(cursor.getInt(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_STATUS)))
                        .setTimeEnd(new Date(cursor.getLong(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_TIME_END))))
                        .setTimeStart(new Date(cursor.getLong(cursor.getColumnIndex(NoteDatabase.NoteEntry.NOTE_TIME_START))))
                        .build();
                list.add(note);
            }
            return list;
        } else
            return null;

    }
}
