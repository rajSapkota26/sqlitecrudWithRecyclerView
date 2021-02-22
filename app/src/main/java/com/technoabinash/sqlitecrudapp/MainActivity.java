package com.technoabinash.sqlitecrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button add, viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add_Btn);
        viewBtn = findViewById(R.id.view_Btn);

        add.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddDataActivity.class));
        });
        viewBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ViewDataActivity.class));
        });
    }
}