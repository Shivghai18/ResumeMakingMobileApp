package com.example.tirth.myapplication;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Project extends AppCompatActivity implements View.OnClickListener {
    EditText e1, e2, e3;
    Button btnback, btnnext;
    CustomDialog cd;
    SharedPreferences sh;
    SharedPreferences.Editor ed;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (cd != null) {
                //  Toast.makeText(this, cd.getQ() + "hohoho" + cd, Toast.LENGTH_SHORT).show();
                if (cd.getQ() == null) {
                    cd = null;
                    return;
                }
                //  Toast.makeText(this, cd.getQ() + "hohoho" + cd, Toast.LENGTH_SHORT).show();
                Intent i = null;
                if (cd.getQ()) {
                    i = new Intent(Project.this, Reference.class);

                } else {
                    i = new Intent(Project.this, FinalActivityDisplay.class);
                //    Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                }
                cd = null;
                startActivity(i);
            } else {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        btnback = (Button) findViewById(R.id.Button);
        btnback.setOnClickListener(this);
        btnnext = (Button) findViewById(R.id.button2);
        btnnext.setOnClickListener(this);
        SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        db.execSQL("Create table if not exists Projects(id INTEGER PRIMARY KEY AUTOINCREMENT,usrid int,Projectname text,Projectdesc text,Companyname text);");
        db.close();

        SharedPreferences sl;
        SharedPreferences.Editor edl;
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 7) {

            edl = sl.edit();
            edl.putString("value", "8");
            edl.commit();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnnext.getId()) {
            try {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
                sh = getSharedPreferences("refid", MODE_PRIVATE);
                int usrid = Integer.parseInt(sh.getString("val", "no  value"));

                db.execSQL("Insert into Projects(usrid,Projectname,Projectdesc,Companyname) values(" + usrid + ",'" + s1 + "','" + s2 + "','" + s3 + "');");
                Toast.makeText(Project.this, "Record inserted", Toast.LENGTH_LONG).show();

                db.close();
                cd = new CustomDialog();
                FragmentManager fg = getFragmentManager();
                cd.show(getSupportFragmentManager(), "Dialog Box");

            } catch (Exception e) {
                Toast.makeText(Project.this, "Record is not inserted", Toast.LENGTH_LONG).show();
//                Intent i = new Intent(Project.this, Reference.class);
//                startActivity(i);
                // Log.d("Exception","Exception in inserting the values");
            }
        } else if (v.getId() == btnback.getId()) {
            Intent i = new Intent(Project.this, Experience.class);
            startActivity(i);

        }
    }
}

