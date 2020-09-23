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


public class Reference extends AppCompatActivity implements View.OnClickListener {
    EditText etname, etadd, etcno, eteid;
    Button btnfin, btnback;
    SharedPreferences sh;
    SharedPreferences.Editor ed;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        etname = (EditText) findViewById(R.id.etref);
        etadd = (EditText) findViewById(R.id.etadd);
        etcno = (EditText) findViewById(R.id.etcno);
        eteid = (EditText) findViewById(R.id.eteid);
        btnback = (Button) findViewById(R.id.btnback);
        btnfin = (Button) findViewById(R.id.btnfin);
        btnfin.setOnClickListener(this);

        SharedPreferences sl;
        SharedPreferences.Editor edl;
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 8) {

            edl = sl.edit();
            edl.putString("value", "9");

            edl.commit();
        }

    }

    @Override
    public void onClick(View view) {
        if (etname.getText().toString().equals("")) {
            etname.setError("Required Field");
        } else if (etadd.getText().toString().equals("")) {
            etadd.setError("Required Field");
        } else if (etcno.getText().toString().equals("")) {
            etcno.setError("Required Field");
        } else if (eteid.getText().toString().equals("")) {
            eteid.setError("Required Field");
        } else {
            if (view.getId() == btnfin.getId()) {

                //sh = getSharedPreferences("cv_pref", MODE_PRIVATE);
                //ed = sh.edit();
                //ed.putString("refid", "1");
                //  ed.commit();

//
                db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
                String str[] = {"Jaideep"};

                sh = getSharedPreferences("refid", MODE_PRIVATE);
                int usrid = Integer.parseInt(sh.getString("val", "no  value"));


                  db.execSQL("Create Table If Not Exists Reference(Ref_id INTEGER primary key AUTOINCREMENT,User_id int,Ref_Name varchar,Ref_Add varchar,Contact_no integer,Email_id varchar);");

               db.execSQL("Insert into Reference(User_id,Ref_Name,Ref_Add,Contact_no,Email_id) values (" + usrid + ",'" + etname.getText().toString() + "','" + etadd.getText().toString() + "'," + Integer.parseInt(etcno.getText().toString()) + ",'" + eteid.getText().toString() + "');");
                Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
                // ed.putString("refid", refid + "");
                // ed.commit();
                Intent i = new Intent(Reference.this, FinalActivityDisplay.class);
                startActivity(i);
            }

            if (view.getId() == btnback.getId()) {
                Intent i = new Intent(Reference.this, Project.class);
                startActivity(i);

            }
        }
    }
}
