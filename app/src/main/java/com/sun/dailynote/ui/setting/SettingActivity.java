package com.sun.dailynote.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sun.dailynote.R;

public class SettingActivity extends AppCompatActivity implements SettingContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}
