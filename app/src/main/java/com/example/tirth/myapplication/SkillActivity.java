package com.example.tirth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SkillActivity extends AppCompatActivity implements OnClickListener, OnItemClickListener {
    EditText edskill;
    Button Add;
    ListView lv;
    String[] items;
    String str1;
    Button btnback, btnnext;
    SharedPreferences sh;
    SharedPreferences ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);

        edskill = (EditText) findViewById(R.id.edtTxtSkillDescription);
        Add = (Button) findViewById(R.id.btnAdd);
        lv = (ListView) findViewById(R.id.lv1);

        items = getResources().getStringArray(R.array.skill);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(ad);
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(this);

        btnnext = (Button) findViewById(R.id.btnnext);
        btnnext.setOnClickListener(this);

        lv.setOnItemClickListener(this);

        SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        db.execSQL("Create table if not exists Skill (id INTEGER PRIMARY KEY AUTOINCREMENT,usrid int, skilldesc text);");
        db.close();


//        if (v.getId() == btnnext.getId()) {
//            SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
//            db.execSQL("Insert into skill(skilldesc) values ('" + str1 + "');");
//            Toast.makeText(SkillActivity.this, "Record inserted", Toast.LENGTH_LONG).show();
//
//            db.close();
//
//            Intent i = new Intent(this, IntrestActivity.class);
//            startActivity(i);
//
//
//        }
        SharedPreferences sl;
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);

        SharedPreferences.Editor edl;
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 4) {
            edl = sl.edit();
            edl.putString("value", "5");
            edl.commit();
        }
    }

    @Override
    public void onClick(View v) {

        String str1;
        str1 = edskill.getText().toString();
        Intent i;
        if (v.getId() == btnnext.getId()) {

            try {
                Toast.makeText(SkillActivity.this, str1, Toast.LENGTH_LONG).show();
                i = new Intent(SkillActivity.this, IntrestActivity.class);
                i.putExtra("skills", str1);

                SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
                sh = getSharedPreferences("refid", MODE_PRIVATE);
                int usrid = Integer.parseInt(sh.getString("val", "no  value"));


                db.execSQL("Insert into Skill(usrid,skilldesc) values (" + usrid + ",'" + str1 + "');");
                Toast.makeText(SkillActivity.this, "Record inserted", Toast.LENGTH_LONG).show();

                db.close();


                startActivity(i);
            } catch (Exception e) {
                Log.d("Exception", "Exception in inserting the values");
            }
        } else {
            i = new Intent(SkillActivity.this, SelectProfession.class);
            startActivity(i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, items[position], Toast.LENGTH_SHORT).show();
        edskill.setText(items[position]);
    }
}






