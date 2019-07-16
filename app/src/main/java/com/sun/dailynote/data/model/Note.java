package com.sun.dailynote.data.model;

import java.sql.Date;

public class Note {
    private int mId;
    private Date mDateCreate;
    private String mContent;
    private String mName;
    private String mImage;
    private Date mTimeStart;
    private int mStatus;
    private Date mTimeEnd;
    private int mCategory;

    public Note() {
    }

    private Note(NoteBuilder builder) {
        mId = builder.mId;
        mDateCreate = builder.mDateCreate;
        mContent = builder.mContent;
        mName = builder.mName;
        mImage = builder.mImage;
        mTimeStart = builder.mTimeStart;
        mStatus = builder.mStatus;
        mTimeEnd = builder.mTimeEnd;
        mCategory = builder.mCategory;
    }

    public int getId() {
        return mId;
    }

    public Date getDateCreate() {
        return mDateCreate;
    }

    public String getContent() {
        return mContent;
    }

    public String getName() {
        return mName;
    }

    public String getImage() {
        return mImage;
    }

    public Date getTimeStart() {
        return mTimeStart;
    }

    public int getStatus() {
        return mStatus;
    }

    public Date getTimeEnd() {
        return mTimeEnd;
    }

    public int getCategory() {
        return mCategory;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setDateCreate(Date dateCreate) {
        mDateCreate = dateCreate;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public void setTimeStart(Date timeStart) {
        mTimeStart = timeStart;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public void setTimeEnd(Date timeEnd) {
        mTimeEnd = timeEnd;
    }

    public void setCategory(int category) {
        mCategory = category;
    }

    public static class NoteBuilder {
        private int mId;
        private Date mDateCreate;
        private String mContent;
        private String mName;
        private String mImage;
        private Date mTimeStart;
        private int mStatus;
        private Date mTimeEnd;
        private int mCategory;

        public NoteBuilder setId(int id) {
            mId = id;
            return this;
        }

        public NoteBuilder setDateCreate(Date dateCreate) {
            mDateCreate = dateCreate;
            return this;
        }

        public NoteBuilder setContent(String content) {
            mContent = content;
            return this;
        }

        public NoteBuilder setName(String name) {
            mName = name;
            return this;
        }

        public NoteBuilder setImage(String image) {
            mImage = image;
            return this;
        }

        public NoteBuilder setTimeStart(Date timeStart) {
            mTimeStart = timeStart;
            return this;
        }

        public NoteBuilder setStatus(int status) {
            mStatus = status;
            return this;
        }

        public NoteBuilder setTimeEnd(Date timeEnd) {
            mTimeEnd = timeEnd;
            return this;
        }

        public NoteBuilder setCategory(int category) {
            mCategory = category;
            return this;
        }

        public Note build() {
            return new Note(this);
        }
    }
}

