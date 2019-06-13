package com.example.eventrecorder_phucnguyen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class showActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
    }

    public void onButtonClose(View view) {
        finish();
    }
}
