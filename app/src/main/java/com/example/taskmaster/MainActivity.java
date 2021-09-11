package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Teamm;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


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


        Button settingButton = findViewById(R.id.settings);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsPage = new Intent(MainActivity.this, settings.class);

                startActivity(settingsPage);
            }
        });

        SharedPreferences sharedName = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedName.getString("userName", "User Name");
        String teamIdFromSettings = sharedName.getString("Team-Id", "");
        System.out.println("---------------- "+teamIdFromSettings);

        TextView textUserNameTask = findViewById(R.id.tskUser);
        textUserNameTask.setText(userName + "'s Tasks");

        RecyclerView allTasksRecyclerView = findViewById(R.id.recViewTask);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allTasksRecyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        List<Task> allTasks = new ArrayList<>();
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));

        System.out.println(allTasks.size());

//            Amplify.API.query(
//                    ModelQuery.list(Task.class
////                            , Task.TEAMM_ID.gt(teamIdFromSettings)
//                    ),
//                    response -> {
//                        System.out.println("------------------------INSIDE API-QUERY------------------------------------------");
//                        System.out.println(response.toString());
//                        for (Task task : response.getData()) {
//                            if(task.getTeammId() == teamIdFromSettings){
//                            Log.i("MyAmplifyApp", task.getTitle());
//                            Log.i("MyAmplifyApp", task.getBody());
//                            Log.i("MyAmplifyApp", task.getState());
//
//                            allTasks.add(task);
//                            System.out.println(task);}
//                        }
//
//                        handler.sendEmptyMessage(1);
//                        //  allTasksRecyclerView.getAdapter().notifyDataSetChanged();
//                        Log.i("MyAmplifyApp", "Out of Loop!");
//
//                    },
//                    error -> Log.e("MyAmplifyApp", "Query failure", error)
//            );

        Amplify.API.query(
                ModelQuery.list(Teamm.class),
                response -> {
                    System.out.println("--------INSIDE API-QUERY----------------------------------------------------------");
                  //  System.out.println("tttttttttttttttttttttttttttttt");
                    System.out.println(response.toString());
                    for (Teamm team : response.getData().getItems()) {
                        Log.i("MyAmplifyApp", team.getName());
                        for (Task task : team.getTasks()) {
                          //  System.out.println(task.getTitle());

                            if (task.getTeammId().equals(teamIdFromSettings) ){
                                Log.i("MyAmplifyApp", task.getTitle());
                            Log.i("MyAmplifyApp", task.getBody());
                            Log.i("MyAmplifyApp", task.getState());
                                allTasks.add(task);
                            System.out.println("based on id ----------------: "+task);

                            }
                        }
                        System.out.println("team after log.iiiiiiiiiiiii: "+team);
                        System.out.println("tttttttttttttttttttttttttttttttttttttttttttt");
                    }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "Out of Loop!");

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

            Button addTask = findViewById(R.id.btn1);
            addTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent addTaskPage = new Intent(MainActivity.this, addTask.class);
                    addTaskPage.putExtra("number", String.valueOf(allTasks.size()));
                    startActivity(addTaskPage);
                }
            });

            System.out.println(allTasks);


    }


    public void TaskListener(Task task){
        Intent intent = new Intent(MainActivity.this, taskDetail.class);
        intent.putExtra("details",task.getTitle()+" "+task.getBody()+" "+ task.getState());

    }


}