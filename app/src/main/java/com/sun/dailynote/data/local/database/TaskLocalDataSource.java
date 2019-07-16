package com.sun.dailynote.data.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.sun.dailynote.R;
import com.sun.dailynote.data.TaskDataSoucre;
import com.sun.dailynote.data.model.Task;
import com.sun.dailynote.ui.BaseCallBack;
import com.sun.dailynote.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TaskLocalDataSource implements TaskDataSoucre.Local {
    private static TaskLocalDataSource sTaskLocalDataSource;
    private static TaskDatabase mTaskDatabase;
    private Context mContext;

    public TaskLocalDataSource(Context context) {
        mContext = context;
        mTaskDatabase = TaskDatabase.getInstance(context,
                Constants.DATABASE_NAME,
                null, Constants.DATABASE_VERSION);
    }

    @Override
    public void getTasks(int nodeId, BaseCallBack<List<Task>> callback) {
        List<Task> tasks = new ArrayList<>();
        Cursor cursor = mTaskDatabase.taskQueryDataWithResult(null,
                TaskDatabase.TaskEntry.TASK_CHECK_ID_NODE + " = ?",
                new String[]{nodeId + ""}, null, null, null, null);
        if(cursor.moveToFirst())
        {
            while (!cursor.moveToNext()) {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(TaskDatabase.TaskEntry.TASK_CHECK_ID)));
                task.setIdNote(cursor.getInt(cursor.getColumnIndex(TaskDatabase.TaskEntry.TASK_CHECK_ID_NODE)));
                task.setContent(cursor.getString(cursor.getColumnIndex(TaskDatabase.TaskEntry.TASK_CHECK_CONTENT)));
                task.setStatus(cursor.getInt(cursor.getColumnIndex(TaskDatabase.TaskEntry.TASK_CHECK_STATUS)));
                tasks.add(task);
            }
            callback.onSuccess(tasks);
        }
        else
        {
            callback.onSuccess(null);
            callback.onMess(mContext.getString(R.string.not_found));
        }
    }

    @Override
    public void upDateTask(Task task, BaseCallBack callBack) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDatabase.TaskEntry.TASK_CHECK_CONTENT, task.getContent());
        contentValues.put(TaskDatabase.TaskEntry.TASK_CHECK_STATUS, task.getStatus());
        int check = mTaskDatabase.taskUpdate(TaskDatabase.TaskEntry.TASK_TABLE_NAME, contentValues,
                "id = ? ", new String[]{String.valueOf(task.getId())});
        if (check > 0) {
            callBack.onMess(mContext.getString(R.string.update_success));
        }
        else {
            callBack.onMess(mContext.getString(R.string.update_fail));
        }
    }
}
