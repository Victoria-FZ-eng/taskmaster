package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button taskA = findViewById(R.id.tsk1);
        taskA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent taskDetail = new Intent(MainActivity.this, taskDetail.class);
                startActivity(taskDetail);
            }
        });



        // lab26 buttons in homepage
//        Button addTask = findViewById(R.id.btn1);
//        addTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent addTaskPage = new Intent(MainActivity.this, addTask.class);
//                startActivity(addTaskPage);
//            }
//        });
//        Button allTasks = findViewById(R.id.btn2);
//        allTasks.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent allTaskPage = new Intent(MainActivity.this, allTasks.class);
//                startActivity(allTaskPage);
//            }
//        });




    }


}