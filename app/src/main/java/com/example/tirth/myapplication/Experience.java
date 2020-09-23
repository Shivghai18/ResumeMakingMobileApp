package com.example.tirth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Experience extends AppCompatActivity implements View.OnClickListener {
    EditText t1, t2, t3, t4;
    Button btnback, btnnext;
    SharedPreferences sh;
    SharedPreferences ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiance);
        t1 = (EditText) findViewById(R.id.editText4);
        t2 = (EditText) findViewById(R.id.editText5);
        t3 = (EditText) findViewById(R.id.editText6);
        t4 = (EditText) findViewById(R.id.editText1);
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(this);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnnext.setOnClickListener(this);
        SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        db.execSQL("Create table if not exists Experience (id INTEGER PRIMARY KEY AUTOINCREMENT,usrid int,Name varchar,Experience int,Designation varchar,Place varchar);");
        db.close();

        SharedPreferences sl;
        SharedPreferences.Editor edl;
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);

        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 6) {
            edl = sl.edit();
            edl.putString("value", "7");
            edl.commit();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnnext) {
            String s1 = t1.getText().toString();
            int s2 = Integer.parseInt(t2.getText().toString());
            String s3 = t3.getText().toString();
            String s4 = t4.getText().toString();
            //Toast.makeText(experience.this,s1+s2+s3+s4, Toast.LENGTH_LONG).show();
            try {
                sh = getSharedPreferences("refid", MODE_PRIVATE);
                int usrid = Integer.parseInt(sh.getString("val", "no  value"));

                SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
                db.execSQL("Insert into Experience(usrid,Name,Experience,Designation,Place) values(" + usrid + ",'" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "');");

                Toast.makeText(Experience.this, "Record inserted", Toast.LENGTH_LONG).show();

                db.close();
                Intent i = new Intent(Experience.this, Project.class);
                startActivity(i);
            } catch (Exception e) {
                Toast.makeText(Experience.this, "Record not inserted", Toast.LENGTH_LONG).show();
                //  Log.d("Exception","Exception in inserting the values");
            }
        } else {
            Intent i = new Intent(Experience.this, IntrestActivity.class);
            startActivity(i);
        }
    }
}
