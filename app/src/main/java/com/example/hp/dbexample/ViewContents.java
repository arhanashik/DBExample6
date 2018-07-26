package com.example.hp.dbexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewContents extends AppCompatActivity{
    VivzDatabaseAdapter adapter;
    private ArrayList<Integer> Id = new ArrayList<>();
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Password = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new VivzDatabaseAdapter(this);
        setContentView(R.layout.list_view);
        listView=findViewById(R.id.listView);
        DisplayAdapter da=new DisplayAdapter(this,adapter.getDetails());
        listView.setAdapter(da);
    }
}
