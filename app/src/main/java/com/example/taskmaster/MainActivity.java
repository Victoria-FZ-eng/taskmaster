package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase appDatabase;

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

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);

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

        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase")
                .allowMainThreadQueries().build();

        List<Task> allTasks=new ArrayList<Task>();
//        List<Task> allTasks= appDatabase.taskDao().getAll();

//        ArrayList<Task> allTasks = new ArrayList<>();
//        allTasks.add(new Task("Task A","Solve Today's Lab","In Progress"));
//        allTasks.add(new Task("Task B","Solve Today's Code Challenge","New"));
//        allTasks.add(new Task("Task C","Do Reading For Tomorrow's Class ","Completed"));
//        allTasks.add(new Task("Task D","Do The Learning Journal ","Assigned"));


        RecyclerView allTasksRecyclerView = findViewById(R.id.recViewTask);
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));

//        Amplify.API.query(
//                ModelQuery.list(com.amplifyframework.datastore.generated.model.Todo.class),
//                response -> {
//                    for (Task task : response.getData()) {
//                        Log.i("MyAmplifyApp", todo.getName());
//                    }
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );



    }


    public void TaskListener(Task task){
        Intent intent = new Intent(MainActivity.this, taskDetail.class);
        intent.putExtra("details",task.title+" "+task.body+" "+ task.state);

    }


}