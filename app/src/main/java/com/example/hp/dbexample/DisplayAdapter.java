package com.example.hp.dbexample;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayAdapter extends BaseAdapter{
    ArrayList<DatabaseModel> model=new ArrayList<>();
    Context context;
    Cursor cursor;
    VivzDatabaseAdapter adapter;
    private ArrayList<Integer> Id = new ArrayList<>();
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Password = new ArrayList<>();

    DisplayAdapter(Context context, Cursor cursor) {
        this.context=context;
        this.cursor=cursor;
    }

    @Override
    public int getCount() {
        return Id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VivzDatabaseAdapter data=new VivzDatabaseAdapter(context);
        View row=convertView;
        if(row==null) {
            row= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        }
        TextView mId=row.findViewById(R.id.enterId);
        TextView mName=row.findViewById(R.id.enterName);
        TextView mPass=row.findViewById(R.id.enterPass);
        mId.setText(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0))));
        mName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        mPass.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        return row;
    }
}
