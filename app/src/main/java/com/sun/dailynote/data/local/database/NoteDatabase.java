package com.sun.dailynote.data.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDatabase extends SQLiteOpenHelper {
    public static class NoteEntry {
        public static final String NOTE_TABLE_NAME = "notes";
        public static final String NOTE_ID = "id";
        public static final String NOTE_NAME = "name";
        public static final String NOTE_CONTENT = "content";
        public static final String NOTE_STATUS = "status ";
        public static final String NOTE_CREATE_DAY = "create_day";
        public static final String NOTE_CATEGORY = "category";
        public static final String NOTE_TIME_START = "starttime";
        public static final String NOTE_TIME_END = "endtime";
        public static final String NOTE_IMAGE = "images";
    }

    private static NoteDatabase sNoteDatabase;

    public static NoteDatabase getInstance(Context context,
                                           String name,
                                           SQLiteDatabase.CursorFactory factory,
                                           int version) {
        if (sNoteDatabase == null)
            sNoteDatabase = new NoteDatabase(context, name, factory, version);
        return sNoteDatabase;
    }

    private NoteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTable = "CREATE TABLE  IF NOT EXISTS "
                + NoteEntry.NOTE_TABLE_NAME
                + "(" + NoteEntry.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  ,"
                + NoteEntry.NOTE_NAME + " VARCHAR(300) NOT NULL ,"
                + NoteEntry.NOTE_CATEGORY + " INT NOT NULL , "
                + NoteEntry.NOTE_CONTENT + " VARCHAR , "
                + NoteEntry.NOTE_IMAGE + " VARCHAR , "
                + NoteEntry.NOTE_CREATE_DAY + " DATE NOT NULL ,"
                + NoteEntry.NOTE_TIME_START + " DATE NOT NULL ,"
                + NoteEntry.NOTE_TIME_END + " DATE NOT NULL ,"
                + NoteEntry.NOTE_STATUS + " INT NOT NULL" + ")";

        db.execSQL(createNoteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor noteQueryDataWithResult(String[] columns, String selection,
                                          String[] selectionArgs, String groupBy, String having,
                                          String orderBy, String limit) throws SQLException {
        SQLiteDatabase database = getWritableDatabase();
        return database.query(NoteEntry.NOTE_TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public long noteInsert(String table, String columrHack, ContentValues contentValues) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(table, columrHack, contentValues);

    }

    public int noteUpdate(String table, ContentValues contentValues, String whereClause, String... whereArgs) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(table, contentValues, whereClause, whereArgs);
    }

    public int noteDelete(String table, String whereClause, String... whereArgs) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(table, whereClause, whereArgs);
    }

}
