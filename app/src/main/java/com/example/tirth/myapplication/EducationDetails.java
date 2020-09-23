package com.example.tirth.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EducationDetails extends AppCompatActivity implements View.OnClickListener {
    TextView txtstr, txtcname, txtcgpa, txtothers, txtsname, txtptage;
    Spinner spn;
    DatePickerDialog dg;
    Context c;
    Button btnnext, btnback;
    SQLiteDatabase db;
    SharedPreferences sh;
    //SharedPreferences.Editor ed;
    RadioGroup rgp;
    RadioButton rba, rbs, rbc;
    static int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_details);
        txtstr = (TextView) findViewById(R.id.txtstr);
        txtstr.setTextColor(Color.BLACK);
        txtcname = (EditText) findViewById(R.id.txtcname);
        txtcgpa = (EditText) findViewById(R.id.txtcgpa);
        txtothers = (EditText) findViewById(R.id.txtothers);
        txtsname = (EditText) findViewById(R.id.txtsname);
        txtptage = (EditText) findViewById(R.id.txtptage);

        rgp = (RadioGroup) findViewById(R.id.rgp);
        rba = (RadioButton) findViewById(R.id.rba);
        rbc = (RadioButton) findViewById(R.id.rbc);
        rbs = (RadioButton) findViewById(R.id.rbs);

        //findViewById(R.id.ra)
        String str[] = {"BCA", "MCA", "B.Sc.", "M.Sc.", "B.Tech", "M.Tech", "BA", "MA", "B.Comm.", "M.Comm.", "BBA", "MBA"};
        spn = (Spinner) findViewById(R.id.spncourse);

        ArrayAdapter adp = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, str);
        spn.setAdapter(adp);

        // dg = new DatePickerDialog(this);
        //dg.setOnDateSetListener(this);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnnext.setOnClickListener(this);
        SharedPreferences sl;
        SharedPreferences.Editor edl;
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 2) {
            sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
            edl = sl.edit();
            edl.putString("value", "3");
            edl.commit();
        //    cnt++;
        }
        txtothers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtothers.getText().toString().equals("")) {
                    spn.setEnabled(true);

                } else {
                    spn.setEnabled(false);

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (txtptage.getText().toString().equals("")) {
            txtptage.setError("Required Field");
        } else if (txtcgpa.getText().toString().equals("")) {
            txtcgpa.setError("Required Field");
        } else if (txtsname.getText().toString().equals("")) {
            txtsname.setError("Required Field");
        } else if (txtcname.getText().toString().equals("")) {
            txtcname.setError("Required Field");
        } else if (view.getId() == btnnext.getId()) {
            Toast.makeText(this, "next button clicked", Toast.LENGTH_LONG).show();


            //  sh = getSharedPreferences("cv_pref", MODE_PRIVATE);
            // ed = sh.edit();
            //    int eduid;
            //  eduid = Integer.parseInt(sh.getString("eduid", "Not found"));
            // eduid++;
            //ed.putString("eduid", eduid + "");
            //ed.commit();

            String rbval = "";
            if (rgp.getCheckedRadioButtonId() == R.id.rbs) {
                rbval = rbs.getText().toString();


            } else if (rgp.getCheckedRadioButtonId() == R.id.rbc) {

                rbval = rbc.getText().toString();

            } else if (rgp.getCheckedRadioButtonId() == R.id.rba) {

                rbval = rba.getText().toString();
            }
            db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
            String str[] = {"Jaideep"};
            // Cursor c = db.rawQuery("Select User_id from Users where Username=?", str);
            //int uId = c.getInt(c.getColumnIndex("User_id"));
            //    int uId = 2;
            db.execSQL("Create Table If Not Exists EduDetails(Edu_id INTEGER primary key AUTOINCREMENT,User_id int,School_Name varchar,College_Name varchar,Passing_date varchar,Percentage float,CGPA float,Course varchar,Stream varchar);");

            sh = getSharedPreferences("refid", MODE_PRIVATE);
            int usrid = Integer.parseInt(sh.getString("val", "no  value"));

            if (txtothers.getText().toString().equals("")) {

                db.execSQL("Insert Into EduDetails(User_id,School_Name,College_Name,Passing_date,Percentage,CGPA,Course,Stream) Values (" + usrid + ",'" + txtsname.getText().toString() + "','" + txtcname.getText().toString() + "','" + "10/11/2017"/*dg.getDatePicker()*/ + "'," + Float.parseFloat(txtptage.getText().toString()) + "," + Float.parseFloat(txtcgpa.getText().toString()) + ",'" + spn.getSelectedItem().toString() + "','" + rbval + "');");
                Intent i = new Intent(EducationDetails.this, SelectProfession.class);
                startActivity(i);

            } else {

                db.execSQL("Insert Into EduDetails(User_id,School_Name,College_Name,Passing_date,Percentage,CGPA,Course,Stream) Values (" + usrid + ",'" + txtsname.getText().toString() + "','" + txtcname.getText().toString() + "','" + "10/11/2017"/*dg.getDatePicker() */ + "'," + Float.parseFloat(txtptage.getText().toString()) + "," + Float.parseFloat(txtcgpa.getText().toString()) + ",'" + txtothers.getText().toString() + "','" + rbval + "');");
                Intent i = new Intent(EducationDetails.this, SelectProfession.class);
                startActivity(i);

            }
            //eduid++;
            //ed.putString("eduid", eduid + "");
            //ed.commit();

        } else {
            Toast.makeText(this, "back button clicked", Toast.LENGTH_SHORT).show();


        }

    }
    //  dg.show();
}

