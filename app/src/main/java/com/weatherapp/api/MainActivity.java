package com.weatherapp.api;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText nameTxt, woeTxt;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<City> cities =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

            }
        });

        //recycler
        rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        retrieve();


    }


    //SAVE
    private void save(String name,String pos)
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        //INSERT
        long result=db.add(name,pos);

        if(result>0)
        {
            nameTxt.setText("");
            woeTxt.setText("");
        }else
        {
            Snackbar.make(nameTxt,"Unable To Insert",Snackbar.LENGTH_SHORT).show();
        }

        //CLOSE
        db.close();

        //refresh
        retrieve();

    }

    //RETRIEVE
    private void retrieve()
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        cities.clear();

        //SELECT
        Cursor c=db.getAllPlayers();

        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);
            Log.e("aaw", "retrieve: "+id );

            //CREATE PLAYER
            City p=new City(name,pos,id);

            //ADD TO PLAYERS
            cities.add(p);
        }

        //SET ADAPTER TO RV
        if(!(cities.size()<1))
        {        adapter=new MyAdapter(this, cities);

            rv.setAdapter(adapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }

    public void delete(int id)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.Delete(id);

        if(result>0)
        {
            this.finish();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();

        rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    boolean theme1 = true;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.theme) {

            if(theme1){

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }
            if(!theme1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            theme1 = !theme1;
            Log.e("ooo", "onOptionsItemSelected: "+String.valueOf(theme1) );




        }

        if(item.getItemId()==R.id.clear){
            DBAdapter db=new DBAdapter(this);
            db.clearDatabase();
            retrieve();
            rv.getAdapter().notifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);

    }

}
