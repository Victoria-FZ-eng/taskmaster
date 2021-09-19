package com.example.taskmaster;

import androidx.annotation.Nullable;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class addTask extends AppCompatActivity {

    String selected;
    private String team = "not selected";
    private EditText titleText;
    private String desc ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleText = findViewById(R.id.editTextTextPersonName2);

        Intent getDesc = getIntent();

        if (getDesc.getType() != null){
            System.out.println("*******************GET FROM INTENT*************************");
            System.out.println("************"+ getDesc.getType());
            System.out.println("************"+ getDesc.getData());
        }
        if (getDesc.getType() != null && getDesc.getType().equals("text/plain")){
            desc = getDesc.getExtras().get(Intent.EXTRA_TEXT).toString();
            Log.i("test Desc",desc);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView numberOfTasks = findViewById(R.id.num);
        Intent intent= getIntent();
        numberOfTasks.setText(intent.getExtras().getString("number"));


        EditText bodyText = findViewById(R.id.editTextTextPersonName3);
       if (desc != ""){
           bodyText.setText(desc);
       }

        Button upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickFile=new Intent(Intent.ACTION_GET_CONTENT);
                pickFile.setType("*/*");
                pickFile=Intent.createChooser(pickFile,"Pick a Photo");
                startActivityForResult(pickFile,1234);
            }
        });
//        upload.setOnClickListener((v)->{
//
//        });

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
              //  Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

       // RadioGroup group =(RadioGroup) findViewById(R.id.radio);

        RadioButton teamA =(RadioButton)  findViewById(R.id.radioButtonTeam1);
        RadioButton teamB = (RadioButton) findViewById(R.id.radioButtonTeam2);
        RadioButton teamC = (RadioButton) findViewById(R.id.radioButtonTeam3);
        teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Radio Button A -----------------------");
                team = "A";
            }
        });
        teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Radio Button B -----------------------");
                team = "B";
            }
        });
        teamC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Radio Button C -----------------------");
                team = "C";
            }
        });
        System.out.println("TEAM: "+ team);

        Button add = findViewById(R.id.btn3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Task task = Task.builder()
                        .teammId(team)
                        .title(titleText.getText().toString())
                        .body(bodyText.getText().toString())
                        .state(selected)
                        .build();

                System.out.println("-------------------------------------************************");
                System.out.println(task.getBody());
                System.out.println("*************************************************");
                System.out.println(team);


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String key=titleText.getText().toString();
        File exampleFile = new File(getApplicationContext().getFilesDir(), "title");
        try {
            InputStream inputStream=getContentResolver().openInputStream(data.getData());
            OutputStream outputStream=new FileOutputStream(exampleFile);
            byte[]buf=new byte[1024];
            int len;
            while ((len=inputStream.read(buf))>0){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.close();

        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                key,
                exampleFile,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }
}