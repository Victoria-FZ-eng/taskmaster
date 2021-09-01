package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // lab26 buttons in homepage

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
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);

        // lab27 commented bellow

//        Button taskA = findViewById(R.id.tsk1);
//        taskA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent taskDetail = new Intent(MainActivity.this, taskDetail.class);
//
//                TextView name = findViewById(R.id.tsk1);
//                String userName= name.getText().toString();
//                taskDetail.putExtra("Name", userName);
//                startActivity(taskDetail);
//            }
//        });
//
//        Button taskB = findViewById(R.id.tsk2);
//        taskB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent taskDetail = new Intent(MainActivity.this, taskDetail.class);
//
//                TextView name = findViewById(R.id.tsk2);
//                String userName= name.getText().toString();
//                taskDetail.putExtra("Name", userName);
//                startActivity(taskDetail);
//            }
//        });
//
//        Button taskC = findViewById(R.id.tsk3);
//        taskC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent taskDetail = new Intent(MainActivity.this, taskDetail.class);
//
//                TextView name = findViewById(R.id.tsk3);
//                String userName= name.getText().toString();
//                taskDetail.putExtra("Name", userName);
//                startActivity(taskDetail);
//            }
//        });

        Button addTask = findViewById(R.id.btn1);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addTaskPage = new Intent(MainActivity.this, addTask.class);
                startActivity(addTaskPage);
            }
        });


        Button settingButton = findViewById(R.id.settings);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsPage = new Intent(MainActivity.this, settings.class);

                startActivity(settingsPage);
            }
        });

        SharedPreferences sharedName = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedName.getString("userName","User Name");

        TextView textUserNameTask = findViewById(R.id.tskUser);
        textUserNameTask.setText(userName+ "'s Tasks");

        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.add(new Task("Task A","Solve Today's Lab","In Progress"));
        allTasks.add(new Task("Task B","Solve Today's Code Challenge","New"));
        allTasks.add(new Task("Task C","Do Reading For Tomorrow's Class ","Completed"));
        allTasks.add(new Task("Task D","Do The Learning Journal ","Assigned"));


        RecyclerView allTasksRecyclerView = findViewById(R.id.recViewTask);

        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));


    }



}