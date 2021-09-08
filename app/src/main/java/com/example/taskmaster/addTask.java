package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;
import java.util.List;

import com.amplifyframework.datastore.generated.model.TaskNew;

public class addTask extends AppCompatActivity {
    //AppDatabase appDatabase;
    String selected;
  //  public List allTasks = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//

    }
    @Override
    protected void onStart() {
        super.onStart();

//        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase")
//                .allowMainThreadQueries().build();

//        List<TaskAmplify> allTasks= appDatabase.taskDao().getAll();
        TextView numberOfTasks = findViewById(R.id.num);
        Intent intent= getIntent();
        numberOfTasks.setText(intent.getExtras().getString("number"));




        EditText titleText = findViewById(R.id.editTextTextPersonName2);
        EditText bodyText = findViewById(R.id.editTextTextPersonName3);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("New");
        arrayList.add("Assigned");
        arrayList.add("InProgress");
        arrayList.add("Complete");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                selected=tutorialsName;
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Button add = findViewById(R.id.btn3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),"Added!",Toast.LENGTH_LONG).show();
//                TaskAmplify task = new TaskAmplify(titleText.getText().toString(),bodyText.getText().toString(),selected);
//                appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase")
//                        .allowMainThreadQueries().build();
//                TaskDao taskDao = appDatabase.taskDao();
//
                TaskNew task = TaskNew.builder()
                        .title(titleText.getText().toString())
                        .body(bodyText.getText().toString())
                        .state(selected)
                        .build();
               // ModelMutation.create(task);

                System.out.println("-------------------------------------************************");
                System.out.println(task.getBody());
                System.out.println("*************************************************");


                Amplify.API.mutate(
                        ModelMutation.create(task),
                        result -> Log.i("MyAmplifyApp", "Created a new task successfully"),
                        error -> Log.e("MyAmplifyApp",  "Error creating task", error)
                );
              //  allTasks.add(task);

                Intent backHome = new Intent(addTask.this, MainActivity.class);
                startActivity(backHome);
            }
        });



    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}