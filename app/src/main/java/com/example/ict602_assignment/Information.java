package com.example.ict602_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Information extends AppCompatActivity implements View.OnClickListener {

    Toolbar infoToolbar;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        infoToolbar = findViewById(R.id.information_toolbar);
        setSupportActionBar(infoToolbar);
        getSupportActionBar().setTitle("Information");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnBack = findViewById(R.id.btnback);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnBack){
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){

            super.onBackPressed();
        }
        return true;
    }
}