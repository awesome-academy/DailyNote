package com.sun.dailynote.ui.notedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sun.dailynote.R;

public class NoteDetailActivity extends AppCompatActivity implements NoteDetailContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
    }
}
