package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class taskDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
        protected void onStart() {
            super.onStart();
            Intent intent= getIntent();
            String name = intent.getExtras().getString("Name");
            String team = intent.getExtras().getString("Team");
            TextView title = findViewById(R.id.titleTask);
            title.setText("Team-"+team+" - "+name);

            String bodyText = intent.getExtras().getString("Body");
            TextView body = findViewById(R.id.taskBody);
            body.setText(bodyText);

            String status = intent.getExtras().getString("State");
            TextView state = findViewById(R.id.taskState);
            state.setText(status);
        }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}