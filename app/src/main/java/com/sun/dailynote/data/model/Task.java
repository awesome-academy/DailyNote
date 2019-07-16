package com.sun.dailynote.data.model;

public class Task {
    private int mId, mIdNote;
    private String mContent;
    private int mStatus;

    public Task() {
    }

    public Task(int id, int idNote, String content, int status) {
        mId = id;
        mIdNote = idNote;
        mContent = content;
        mStatus = status;
    }

    public Task(int id) {
        mId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return mId == task.mId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getIdNote() {
        return mIdNote;
    }

    public void setIdNote(int idNote) {
        mIdNote = idNote;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }


}
