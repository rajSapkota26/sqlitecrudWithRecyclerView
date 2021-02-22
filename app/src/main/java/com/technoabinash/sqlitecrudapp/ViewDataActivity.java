package com.technoabinash.sqlitecrudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {
ArrayList<User> listUser;
ViewDataAdapter adapter;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        SqliteHelper db=new SqliteHelper(ViewDataActivity.this);
        listUser=db.getUser();
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewDataActivity.this));
        adapter=new ViewDataAdapter(listUser,ViewDataActivity.this);
        recyclerView.setAdapter(adapter);
    }
}