package com.example.tirth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {// implements Spinner.OnItemSelectedListener{
    Spinner sp;
    Button login;
    Button signUp;
    SQLiteDatabase db;
    EditText usrnm;
    EditText pass;
    EditText nm;
    EditText secans;
    EditText email;
    EditText conpass;
    SharedPreferences s;
    Spinner spque;
    String strnm;
    String strusrnm;
    String stremail;
    String strgender;
    String strpass;
    String strans;
    String strque;
    String strconpass;
    Boolean submitFlag = true;
    RadioButton rdmale;
    RadioButton rdfemale;
    RadioButton rdother;
    Date dateob;
    EditText dobi;
    String strdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = (Button) findViewById(R.id.btnsign);
        String questions[] = {"What is your best friend name", "What is your native place", "What is your favourite sport"};
        sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, questions);
        sp.setAdapter(arrayAdapter);
        db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        db.execSQL("Create table if not exists SignUp(userid INTEGER primary key AUTOINCREMENT,name varchar,email varchar,dob varchar,gender varchar,username varchar,password varchar,securityque varchar,securityans varchar)");
        dateob = new Date();
        dobi = (EditText) findViewById(R.id.eddob);
        //   s = getSharedPreferences("Signup", MODE_PRIVATE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT).show();
                nm = (EditText) findViewById(R.id.ednm);
                usrnm = (EditText) findViewById(R.id.edusr);
                pass = (EditText) findViewById(R.id.edpass);
                email = (EditText) findViewById(R.id.edemail);
                secans = (EditText) findViewById(R.id.edans);
                conpass = (EditText) findViewById(R.id.edconpass);
                rdmale = (RadioButton) findViewById(R.id.rdm);
                rdfemale = (RadioButton) findViewById(R.id.rdf);
                rdother = (RadioButton) findViewById(R.id.rdo);
                strdate = dobi.getText().toString();
                if (TextUtils.isEmpty(strdate)) {
                    dobi.setError("Enter your name");
                    submitFlag = false;
                }

                strnm = nm.getText().toString();
                if (TextUtils.isEmpty(strnm)) {
                    nm.setError("Enter your name");
                    submitFlag = false;
                }

                strusrnm = usrnm.getText().toString();
                if (TextUtils.isEmpty(strusrnm)) {
                    usrnm.setError("Enter username");
                    submitFlag = false;
                }
                stremail = email.getText().toString();
                if (TextUtils.isEmpty(stremail)) {
                    email.setError("Enter your email adress");
                    submitFlag = false;
                }

                strpass = pass.getText().toString();
                if (TextUtils.isEmpty(strpass)) {
                    pass.setError("Enter password");
                    submitFlag = false;
                }
                strans = secans.getText().toString();
                if (TextUtils.isEmpty(strans)) {
                    secans.setError("Enter your anwer");
                    submitFlag = false;
                }
                strconpass = conpass.getText().toString();
                if (TextUtils.isEmpty(strconpass)) {
                    conpass.setError("Field should not be empty");
                    submitFlag = false;
                }
                strque = sp.getSelectedItem().toString();
                if (rdmale.isChecked() == true) {
                    strgender = rdmale.getText().toString();
                } else {
                    if (rdfemale.isChecked() == true) {
                        strgender = rdfemale.getText().toString();
                    } else {
                        strgender = rdother.getText().toString();
                    }
                }
                if (TextUtils.isEmpty(strgender)) {
                    submitFlag = false;
                }
                if (strconpass.equals(strpass) && submitFlag == true) {
//                    usrid = Integer.parseInt(s.getString("id", "Not found"));
//                    Toast.makeText(MainActivity.this, usrid+"", Toast.LENGTH_SHORT).show();
                    //

                    //    Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(MainActivity.this, "ac", Toast.LENGTH_SHORT).show();

                    //    db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);

                    db.execSQL("insert into SignUp (name,email,dob,gender,username,password,securityque,securityans) values ('" + strnm + "','" + stremail + "','" + strdate + "','" + strgender + "','" + strusrnm + "','" + strpass + "','" + strque + "','" + strans + "')");
                    // refId=usrid;
                   // Toast.makeText(MainActivity.this, "b", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();

                    submitFlag = true;

                }


            }
        });
    }


// z

}
