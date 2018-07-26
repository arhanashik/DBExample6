package com.example.hp.dbexample;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    VivzDatabaseAdapter vivzDatabaseAdapter;
    EditText username, password, name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        name1 = findViewById(R.id.editText3);
        vivzDatabaseAdapter = new VivzDatabaseAdapter(this);
    }

    public void addUser(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        long id = vivzDatabaseAdapter.insertData(user, pass);
        if (id < 0) {
            Message.message(this, "Unsuccessful");
        } else {
            Message.message(this, "Successfully inserted a row");
        }
    }

    public void viewAllDetail(View view) {
        Intent intent=new Intent(this, ViewContents.class);
        startActivity(intent);
      //  String data = vivzDatabaseAdapter.getAllData();
        //Message.message(this, data);
    }

    public void deleteData(View view) {
        int count= vivzDatabaseAdapter.deleteRow();
        Message.message(this, ""+count);
    }

    public void updateData(View view) {
        vivzDatabaseAdapter.updateName("chirag", "hunny");
    }

    public void getDetails(View view) {
        if (name1.getText().toString().equals("")) {
            Message.message(this,"Enter name first");
        }
        else
            {
            String s1 = name1.getText().toString();
            String sub1 = s1.substring(0, s1.indexOf(" "));
            String sub2 = s1.substring(s1.indexOf(" ") + 1);
            String s2 = vivzDatabaseAdapter.getData(sub1, sub2);
            Message.message(this, s2);
        }
    }
}
