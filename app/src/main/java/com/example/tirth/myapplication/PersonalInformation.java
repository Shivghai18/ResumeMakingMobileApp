package com.example.tirth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInformation extends AppCompatActivity implements View.OnClickListener {

    EditText etuname, etaddress1, etaddress2, etstate, etcountry, etpin, etmail, etcnumber, etnationality, etcity, etdate;
    Button btnback, btnnext;
    SQLiteDatabase db;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    int usrid;
    static int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        etuname = (EditText) findViewById(R.id.etuname);
        etaddress1 = (EditText) findViewById(R.id.etaddress1);
        etaddress2 = (EditText) findViewById(R.id.etaddress2);
        etstate = (EditText) findViewById(R.id.etstate);
        etcountry = (EditText) findViewById(R.id.etcountry);
        etpin = (EditText) findViewById(R.id.etpin);
        etmail = (EditText) findViewById(R.id.etmail);
        etcnumber = (EditText) findViewById(R.id.etcnumber);
        etnationality = (EditText) findViewById(R.id.etnationality);
        etcity = (EditText) findViewById(R.id.etcity);
        etdate = (EditText) findViewById(R.id.etdate);

        btnback = (Button) findViewById(R.id.btnback);
        btnnext = (Button) findViewById(R.id.btnnext);

        btnback.setOnClickListener(this);
        btnnext.setOnClickListener(this);
        SharedPreferences sl;
        SharedPreferences.Editor edl;

        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        Log.d("abcde", num + "");
        if (num == 1) {
            edl = sl.edit();
            edl.putString("value", "2");
            edl.commit();
            cnt++;
            int val = Integer.parseInt(sl.getString("value", "no such activity"));

            Log.d("abcde", val + "");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnnext.getId()) {
            // sp = getSharedPreferences("Personal_information", MODE_PRIVATE);
            //spe = sp.edit();
            //spe.putString("P_id", "1");
            //spe.commit();
            //  String str = sp.getString("P_id", "Not Found");
            int Pid;
            // int Pid=3;
            // Pid++;
            //spe.putString("P_id", "2");
            //spe.commit();

            String str1;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            int str8;
            String str9;
            String str10;
            int str11;

            str1 = etuname.getText().toString();
            str2 = etaddress1.getText().toString();
            str3 = etaddress2.getText().toString();
            str4 = etcity.getText().toString();
            str5 = etstate.getText().toString();
            str6 = etcountry.getText().toString();
            str7 = etmail.getText().toString();
            str8 = Integer.parseInt(etcnumber.getText().toString());
            str9 = etnationality.getText().toString();
            str10 = etdate.getText().toString();
            str11 = Integer.parseInt(etpin.getText().toString());
            // int uId = 2;
            //Pid = Integer.parseInt(sp.getString("P_id", "Not Found"));
            //Log.d("abcdef", Pid + "");
            db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
            db.execSQL("create table if not exists PersonalInforr(P_id INTEGER primary key AUTOINCREMENT,usrid int,Name varchar,Address1 varchar,Address2 varchar,City varchar,State varchar,Country varchar,Pincode int,Email_id varchar,Number int,Nationality varchar,Bdate varchar)");
            //Pid++;
            //     usrid=HomeScreen.refId;
            sp = getSharedPreferences("refid", MODE_PRIVATE);
            usrid = Integer.parseInt(sp.getString("val", "no  value"));

            db.execSQL("insert into PersonalInforr(usrid,Name,Address1,Address2,City,State,Country,Pincode,Email_id,Number,Nationality,Bdate) values (" + usrid + ",'" + str1 + "','" + str2 + "','" + str3 + "','" + str4 + "','" + str5 + "','" + str6 + "'," + str11 + ",'" + str7 + "'," + str8 + ",'" + str9 + "','" + str10 + "');");

            //spe.putString("P_id", Pid + "");
            //spe.commit();

            Intent i = new Intent(this, EducationDetails.class);
            //i.putExtra("Value1", "This value one for Other Activity ");
            //i.putExtra("Value2", "This value Second Activity");
            startActivity(i);

            // Toast.makeText(PersonalInformation.this, " Data Inserted ", Toast.LENGTH_SHORT).show();
        } else {
            //  Toast.makeText(PersonalInformation.this, " Error ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, HomeScreen.class);
            startActivity(i);
        }

    }
}
