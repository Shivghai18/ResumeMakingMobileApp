package com.example.tirth.myapplication;

import android.content.SharedPreferences;
import android.util.Log;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IntrestActivity extends AppCompatActivity implements OnClickListener, OnItemClickListener {
    EditText edintrest;
    Button Add;
    ListView lv;
    String[] items;
    String str1;
    Button btnback, btnnext;
    SharedPreferences sh;
    SharedPreferences ed;
    //String Programmer,Designer,Networker;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrest);

        edintrest = (EditText) findViewById(R.id.etinterest);
        //Add = (Button) findViewById(R.id.btnAdd);
        //  Add.setOnClickListener(this);
        btnback = (Button) findViewById(R.id.btnback);
        btnnext = (Button) findViewById(R.id.btnnext);

        btnback.setOnClickListener(this);
        btnnext.setOnClickListener(this);


        lv = (ListView) findViewById(R.id.listView1);

        Intent i = getIntent();
        str1 = i.getStringExtra("skills");
        Toast.makeText(IntrestActivity.this, str1, Toast.LENGTH_LONG).show();


        if (str1.equalsIgnoreCase("Programmer")) {
            items = getResources().getStringArray(R.array.Programmer);
        } else if (str1.equalsIgnoreCase("Designer")) {
            items = getResources().getStringArray(R.array.Designer);
        } else if (str1.equalsIgnoreCase("Networker"))

        {
            items = getResources().getStringArray(R.array.Networker);

        }
        //items = getResources().getStringArray(R.array.Programmer);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(this);


        SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        db.execSQL("Create table if not exists Interest (id INTEGER PRIMARY KEY AUTOINCREMENT,usrid int, Intdesc text);");
        db.close();

        SharedPreferences sl;
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 5) {

            SharedPreferences.Editor edl;
            edl = sl.edit();
            edl.putString("value", "6");
            edl.commit();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast.makeText(this, items[position], Toast.LENGTH_SHORT).show();
        edintrest.setText(items[position]);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        String str1;

        if (v.getId() == btnnext.getId()) {
            str1 = edintrest.getText().toString();
            try {
                sh = getSharedPreferences("refid", MODE_PRIVATE);
                int usrid = Integer.parseInt(sh.getString("val", "no  value"));

                SQLiteDatabase db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
                db.execSQL("Insert into Interest(usrid,Intdesc) values (" + usrid + ",'" + str1 + "');");
                Toast.makeText(IntrestActivity.this, "Record inserted", Toast.LENGTH_LONG).show();
                db.close();
            } catch (Exception e) {
                Log.d("Exception", "Exception in inserting the values");
            }


            Intent i = new Intent(IntrestActivity.this, Experience.class);
            startActivity(i);

        } else if (v.getId() == btnback.getId()) {
            Intent i = new Intent(IntrestActivity.this, SkillActivity.class);
            startActivity(i);

        }
    }
}
