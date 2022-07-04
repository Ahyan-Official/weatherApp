package com.weatherapp.api;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    String[] CitiesName ={"London","Paris","Dublin","Milan","Rome","Sofia"};


    EditText nameTxt, woeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);





        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        woeTxt = (EditText) findViewById(R.id.WoeidEditTxt);
        Button savebtn= (Button) findViewById(R.id.saveBtn);



        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, CitiesName);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(CitiesName[i].equals("London")){

                    woeTxt.setText("44418");
                }else if(CitiesName[i].equals("Paris")){

                    woeTxt.setText("615702");

                }else if(CitiesName[i].equals("Dublin")){

                    woeTxt.setText("560743");

                }else if(CitiesName[i].equals("Milan")){

                    woeTxt.setText("718345");

                }else if(CitiesName[i].equals("Rome")){

                    woeTxt.setText("721943");

                }else if(CitiesName[i].equals("Sofia")){

                    woeTxt.setText("839722");

                }

                //woeTxt.setText(CitiesName[i]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameTxt.getText().toString().isEmpty() && !woeTxt.getText().toString().isEmpty()){

                    save(nameTxt.getText().toString(), woeTxt.getText().toString());

                }
            }
        });

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
       // retrieve();

    }


}