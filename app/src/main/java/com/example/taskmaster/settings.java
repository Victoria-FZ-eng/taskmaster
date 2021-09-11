package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class settings extends AppCompatActivity {

    private String team = "not selected";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RadioButton teamA =(RadioButton)  findViewById(R.id.radioButton);
        RadioButton teamB = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton teamC = (RadioButton) findViewById(R.id.radioButton3);
        teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team = "A";
            }
        });
        teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team = "B";
            }
        });
        teamC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team = "C";
            }
        });

        Button saveName = findViewById(R.id.saveName);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences nameShare = PreferenceManager.getDefaultSharedPreferences(settings.this);
                SharedPreferences.Editor sharedPrefEdit = nameShare.edit();

                EditText nameFeild= findViewById(R.id.enterUserName);
                String userName = nameFeild.getText().toString();

                sharedPrefEdit.putString("userName",userName);
                sharedPrefEdit.putString("Team-Id",team);
                sharedPrefEdit.apply();

                Intent home = new Intent(settings.this, MainActivity.class);
                startActivity(home);
            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}