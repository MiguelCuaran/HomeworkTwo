package com.e.homework2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.zip.Inflater;

public class Home extends AppCompatActivity {


    //call data base class connection
    DatabaseHelper db;
    //create a listview variable
    ListView userList;
    //Create an array list variable
    ArrayList<String> ListItem;
    //create an adapter variable
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //instance DB connection
        db = new DatabaseHelper(this);
        //create an empty array
        ListItem = new ArrayList<>();
        //call listview id
        userList = findViewById(R.id.lista);
        //List users information
        viewData();

        //Events
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userList.getItemAtPosition(i).toString();
                Toast.makeText(Home.this, "info" + text, Toast.LENGTH_SHORT).show();
                ;
            }
        });

        registerForContextMenu(userList);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Cursor cursor = db.SelectUsersData();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.update:
                Intent intent= new Intent(this,Update.class);
                startActivity(intent);
                break;
            case R.id.delete:


                break;
            default:
                return super.onContextItemSelected(item);
        }

        return super.onContextItemSelected(item);
    }

    private void viewData() {
        Cursor cursor = db.SelectUsersData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this,
                    "Empty Data Base", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                String val;
                val = cursor.getString(0) + " " + cursor.getString(1) + "\n" + cursor.getString(2);
                ListItem.add(val);


            }
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.test_list_item, ListItem);
            userList.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.male:
                Toast.makeText(this, "Select Male", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.female:
                Toast.makeText(this, "Select Female", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.all:
                Toast.makeText(this, "Select All", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.logout:
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                finish();
                return  true;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }
}
