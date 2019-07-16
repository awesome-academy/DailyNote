package com.sun.dailynote.data.local.repository;

import com.sun.dailynote.data.TaskDataSoucre;
import com.sun.dailynote.data.model.Task;
import com.sun.dailynote.ui.BaseCallBack;

import java.util.List;

public class TaskRepository implements TaskDataSoucre.Local {
    private TaskDataSoucre mTaskDataSoucre;

    public TaskRepository(TaskDataSoucre taskDataSoucre) {
        mTaskDataSoucre = taskDataSoucre;
    }

    @Override
    public void getTasks(int nodeId, BaseCallBack<List<Task>> callback) {

    }

    @Override
    public void upDateTask(Task Task, BaseCallBack callBack) {

    }
}
