package com.sun.dailynote.data;

import com.sun.dailynote.data.model.Task;
import com.sun.dailynote.ui.BaseCallBack;

import java.util.List;

public interface TaskDataSoucre {
    interface Local {
        void getTasks(int nodeId, BaseCallBack<List<Task>> callback);

        void upDateTask(Task Task, BaseCallBack callBack);
    }

    interface Remote {

    }
}
