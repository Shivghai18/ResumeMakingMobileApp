package com.example.tirth.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectProfession extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText etname;
    Button btnext, btnback;
    ListView lv12;
    SQLiteDatabase db;
    SharedPreferences sh;
    //  SharedPreferences.Editor spe;
    String sel;
    String[] education = {"CEO", "President", "Chairman", "Employee", "Intern"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profession);


        ArrayAdapter<String> la = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, education);

        lv12 = (ListView) findViewById(R.id.lv);

        lv12.setAdapter(la);

        lv12.setOnItemClickListener(this);

        etname = (EditText) findViewById(R.id.etname);
        btnext = (Button) findViewById(R.id.btnnext);
        btnback = (Button) findViewById(R.id.btnback);

        btnext.setOnClickListener(this);
        btnback.setOnClickListener(this);
        SharedPreferences sl;
        SharedPreferences.Editor edl;

        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);

        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 3) {
            sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
            edl = sl.edit();
            edl.putString("value", "4");
            edl.commit();
        }
        etname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etname.getText().toString().equals("")) {
                    lv12.setEnabled(true);
                } else {
                    lv12.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onClick(View v) {

        if (v.getId() == btnext.getId()) {

            //String str = sp.getString("Pr_id", "Not Found");
            //int Pr_id = Integer.parseInt(str);
            //spe.putString("Pr_id", "1");
            //int Prid = 3;

            String name = etname.getText().toString();
//            sp = getSharedPreferences("Prof", MODE_PRIVATE);
            //          spe = sp.edit();
            //        Prid = Integer.parseInt(sp.getString("Pr_name", "Trushang"));
//                Prid=Integer.parseInt(name);
            //Toast.makeText(this, Prid+"", Toast.LENGTH_SHORT).show();
            //Prid++;
            //      spe.putString("Pr_name",Prid+"");
            //    spe.commit();
            db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
            db.execSQL("create table if not exists Profession(Pr_id INTEGER primary key AUTOINCREMENT,usrid int,Pr_name varchar)");

            //  db.execSQL("insert into Profession(usrid,Pr_name) values (" + HomeScreen.refId + ",'" + name.toString() + "')");
            sh = getSharedPreferences("refid", MODE_PRIVATE);
            int usrid = Integer.parseInt(sh.getString("val", "no  value"));


            if (etname.getText().toString().equals("")) {

                db.execSQL("Insert into Pro" +
                        "fession(usrid,Pr_name)  values (" + usrid + ",'" + sel + "')");
            } else {
                db.execSQL("Insert into Profession(usrid,Pr_name) values (" + usrid + ",'" + etname.getText().toString() + "')");
                Toast.makeText(this, etname.getText().toString(), Toast.LENGTH_LONG).show();
            }
            Intent in = new Intent(SelectProfession.this, SkillActivity.class);
            //in.putExtra("Value1", "This value one for Other Activity ");
            //in.putExtra("Value2", "This value Second Activity");
            startActivity(in);
        }

        if (v.getId() == btnback.getId()) {
            Intent in = new Intent(SelectProfession.this, EducationDetails.class);
            //in.putExtra("Value1", "This value one for Other Activity ");
            //in.putExtra("Value2", "This value Second Activity");
            startActivity(in);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String education = String.valueOf(parent.getItemAtPosition(position));
        Toast.makeText(this, education, Toast.LENGTH_LONG).show();
        sel = education;
    }
}
