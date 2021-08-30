package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class taskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }
    @Override
        protected void onStart() {
            super.onStart();
            Intent intent= getIntent();
            String name = intent.getExtras().getString("Name");
            TextView title = findViewById(R.id.titleTask);
            title.setText(name);
        }

}