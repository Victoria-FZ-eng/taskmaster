package com.example.taskmaster;

import androidx.annotation.NonNull;
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
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.TaskAmplify;
import com.amplifyframework.datastore.generated.model.Todo;

import com.amplifyframework.datastore.generated.model.AmplifyModelProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //AppDatabase appDatabase;


    TextView textView;
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
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

        //appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase")
          //      .allowMainThreadQueries().build();

        Amplify.DataStore.query(TaskAmplify.class,
                queryMatches -> {
                    if (queryMatches.hasNext()) {
                        Log.i("MyAmplifyApp", "Successful query, found tasks.");
                    } else {
                        Log.i("MyAmplifyApp", "Successful query, but no tasks.");
                    }
                },
                error -> Log.e("MyAmplifyApp",  "Error retrieving tasks", error)
        );


        RecyclerView allTasksRecyclerView = findViewById(R.id.recViewTask);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allTasksRecyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        List<TaskAmplify> allTasks=new ArrayList<>();
//        List<Task> allTasks= appDatabase.taskDao().getAll();

//        ArrayList<Task> allTasks = new ArrayList<>();
//        allTasks.add(new Task("Task A","Solve Today's Lab","In Progress"));
//        allTasks.add(new Task("Task B","Solve Today's Code Challenge","New"));
//        allTasks.add(new Task("Task C","Do Reading For Tomorrow's Class ","Completed"));
//        allTasks.add(new Task("Task D","Do The Learning Journal ","Assigned"));

        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));

        Amplify.API.query(
                ModelQuery.list(TaskAmplify.class),
                response -> {
                    for (TaskAmplify task : response.getData()) {
                        Log.i("MyAmplifyApp", task.getTitle());
                        Log.i("MyAmplifyApp", task.getBody());
                        Log.i("MyAmplifyApp", task.getState());

                        allTasks.add(task);
                        System.out.println(task);
                    }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp","Out of Loop!");
                    allTasksRecyclerView.getAdapter().notifyDataSetChanged();
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );





        System.out.println(allTasks);

    }


    public void TaskListener(TaskAmplify task){
        Intent intent = new Intent(MainActivity.this, taskDetail.class);
        intent.putExtra("details",task.getTitle()+" "+task.getBody()+" "+ task.getState());

    }


}