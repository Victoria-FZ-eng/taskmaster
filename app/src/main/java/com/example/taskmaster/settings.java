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

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button saveName = findViewById(R.id.saveName);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences nameShare = PreferenceManager.getDefaultSharedPreferences(settings.this);
                SharedPreferences.Editor sharedPrefEdit = nameShare.edit();

                EditText nameFeild= findViewById(R.id.enterUserName);
                String userName = nameFeild.getText().toString();

                sharedPrefEdit.putString("userName",userName);
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