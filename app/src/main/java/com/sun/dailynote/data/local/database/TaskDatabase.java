package com.sun.dailynote.data.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDatabase extends SQLiteOpenHelper {

    public static class TaskEntry {
        public static final String TASK_CHECK_ID = "id";
        public static final String TASK_CHECK_ID_NODE = "idnode";
        public static final String TASK_CHECK_CONTENT = "content";
        public static final String TASK_CHECK_STATUS = "status";
        public static final String TASK_TABLE_NAME = "todockeck";
    }

    private static TaskDatabase sTaskDatabase;

    public static TaskDatabase getInstance(Context context,
                                           String name,
                                           SQLiteDatabase.CursorFactory factory,
                                           int version) {
        if (sTaskDatabase == null)
            sTaskDatabase = new TaskDatabase(context, name, factory, version);
        return sTaskDatabase;
    }

    private TaskDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaskCheckTable = "CREATE TABLE IF NOT EXISTS "
                + TaskEntry.TASK_TABLE_NAME
                + " (" + TaskEntry.TASK_CHECK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  ,"
                + TaskEntry.TASK_CHECK_ID_NODE + " INTEGER NOT NULL,"
                + TaskEntry.TASK_CHECK_CONTENT + " VARCHAR(300) NOT NULL,"
                + TaskEntry.TASK_CHECK_STATUS + " INT NOT NULL ,"
                + "FOREIGN KEY(" + TaskEntry.TASK_CHECK_ID_NODE + ")"
                + " REFERENCES " + NoteDatabase.NoteEntry.NOTE_TABLE_NAME + "(" + NoteDatabase.NoteEntry.NOTE_ID + ")"
                + "ON UPDATE CASCADE "
                + "ON DELETE CASCADE"
                + ")";
        db.execSQL(createTaskCheckTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor taskQueryDataWithResult(String[] columns, String selection,
                                          String[] selectionArgs, String groupBy, String having,
                                          String orderBy, String limit) throws SQLException {
        SQLiteDatabase database = getWritableDatabase();
        return database.query(TaskEntry.TASK_TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public long taskInsert(String table, String columrHack, ContentValues contentValues) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(table, columrHack, contentValues);

    }

    public int taskUpdate(String table, ContentValues contentValues, String whereClause, String... whereArgs) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(table, contentValues, whereClause, whereArgs);
    }

    public int taskDelete(String table, String whereClause, String... whereArgs) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(table, whereClause, whereArgs);
    }

}
