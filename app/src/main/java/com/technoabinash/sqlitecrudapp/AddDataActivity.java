package com.technoabinash.sqlitecrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {
    TextView name, email, mobile, address;
    TextView save;
    SqliteHelper db;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        init();

        save.setOnClickListener(view -> {saveData(); });
        if (getIntent().getIntExtra("edit", 0) == 26) {
           updateUserM();
        }


    }

    private void updateUserM() {
        //change button from save to Update
        save.setText("Update");
        Intent intent = getIntent();
        //set all value which we send from recycler view
        name.setText(intent.getStringExtra("uName"));
        email.setText(intent.getStringExtra("uEmail"));
        mobile.setText(intent.getStringExtra("uMobile"));
        address.setText(intent.getStringExtra("uAddress"));

        save.setOnClickListener(view -> {
            // get new data after editing value
            int id = intent.getIntExtra("uId", 0);
            user.setId(id);
            user.setName(name.getText().toString());
            user.setEmail(email.getText().toString());
            user.setMobile(mobile.getText().toString());
            user.setAddress(address.getText().toString());
            //finally send value to database
            db.updateUser(user);
            notification("data Updated ");
        });
    }

    private void init() {
        name = findViewById(R.id.etx_name);
        email = findViewById(R.id.etx_email);
        mobile = findViewById(R.id.etx_mobile);
        address = findViewById(R.id.etx_address);
        save = findViewById(R.id.save_Btn);
        user = new User();
        db = new SqliteHelper(AddDataActivity.this);
    }

    private void saveData() {
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setMobile(mobile.getText().toString());
        user.setAddress(address.getText().toString());
        db.addUser(user);
        notification("data Saved ");
    }

    private void notification(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        startActivity(new Intent(AddDataActivity.this, ViewDataActivity.class));
    }
}