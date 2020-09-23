package com.example.tirth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class FinalActivityDisplay extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Cursor dbMain;
    Cursor dbPersonal;
    Cursor dbEducation;
    Cursor dbProfession;
    Cursor dbSkill;
    Cursor dbInterest;
    Cursor dbExperience;
    Cursor dbProject;
    Cursor dbReference;
    TextView etcv;
    SharedPreferences sh;
    Button btnlogout;
    SharedPreferences sl;
    SharedPreferences.Editor edl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_display);

        etcv = (TextView) findViewById(R.id.txtcv);
        btnlogout = (Button) findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(this);
        sh = getSharedPreferences("refid", MODE_PRIVATE);
        int usrid = Integer.parseInt(sh.getString("val", "no  value"));


        db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        dbMain = db.rawQuery("select * from SignUp where userid=" + usrid, null);
        dbPersonal = db.rawQuery("select * from PersonalInforr where usrid=" + usrid, null);
        dbEducation = db.rawQuery("select * from EduDetails where User_id=" + usrid, null);
        dbProfession = db.rawQuery("select * from Profession where usrid=" + usrid, null);
        dbSkill = db.rawQuery("select * from Skill where usrid=" + usrid, null);
        dbInterest = db.rawQuery("select * from Interest where usrid=" + usrid, null);
        dbProject = db.rawQuery("select * from Projects where usrid=" + usrid, null);
        //      dbReference = db.rawQuery("select * from --where ", null);
        dbExperience = db.rawQuery("select * from Experience", null);

        dbMain.moveToFirst();
        dbPersonal.moveToFirst();
        dbEducation.moveToFirst();
        dbProfession.moveToFirst();
        dbSkill.moveToFirst();
        dbInterest.moveToFirst();
        dbProject.moveToFirst();
        //   dbReference.moveToFirst();
        dbExperience.moveToFirst();

        String name = dbPersonal.getString(dbPersonal.getColumnIndex("Name"));
        String address = dbPersonal.getString(dbPersonal.getColumnIndex("Address1"));
        String strcity = dbPersonal.getString(dbPersonal.getColumnIndex("City"));
        String strstate = dbPersonal.getString(dbPersonal.getColumnIndex("State"));
        String strpin = dbPersonal.getString(dbPersonal.getColumnIndex("Pincode"));
        String stremail = dbPersonal.getString(dbPersonal.getColumnIndex("Email_id"));
        String strno = dbPersonal.getString(dbPersonal.getColumnIndex("Number"));
        String strschool = dbEducation.getString(dbEducation.getColumnIndex("School_Name"));
        String strclgnm = dbEducation.getString(dbEducation.getColumnIndex("College_Name"));
        String strper = dbEducation.getString(dbEducation.getColumnIndex("Percentage"));
        String strcgpa = dbEducation.getString(dbEducation.getColumnIndex("CGPA"));
        String strcourse = dbEducation.getString(dbEducation.getColumnIndex("Course"));
        String strstream = dbEducation.getString(dbEducation.getColumnIndex("Stream"));
        String strprofessionname = dbProfession.getString(dbProfession.getColumnIndex("Pr_name"));
        String strinterest = dbInterest.getString(dbInterest.getColumnIndex("Intdesc"));
        String strskill = dbSkill.getString(dbSkill.getColumnIndex("skilldesc"));
        String strexp = dbExperience.getString(dbExperience.getColumnIndex("Experience"));
        String strdesign = dbExperience.getString(dbExperience.getColumnIndex("Designation"));
        String strplace = dbExperience.getString(dbExperience.getColumnIndex("Place"));
        String strprojname = dbProject.getString(dbProject.getColumnIndex("Projectname"));
        String strprojdesc = dbProject.getString(dbProject.getColumnIndex("Projectdesc"));
        String strcomname = dbProject.getString(dbProject.getColumnIndex("Companyname"));
//
//

        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        int num = Integer.parseInt(sl.getString("value", "no such activity"));
        if (num == 9) {

            edl = sl.edit();
            edl.putString("value", "10");
            edl.commit();
        }
        etcv.setText("CV");
        etcv.setText(Html.fromHtml("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><u>CURRICULUM VITAE</u></b><br/>------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------<br/>Name:" + name + "<br/>Address:" + address + "<br/>City:" + strcity + "<br/>State:" + strstate + "<br/>Pin Code:" + strpin + "<br/>Email:" + stremail + "<br/>Contact No:" + strno + "<br/>"
                + "-----------------------------------------------------------------------------------------<br/><table border=1><tr><td><b><u>Career Objective</u></b></td></tr> </table><br/><ul><li>To explore myself and seek knowledge so that I can be the best at whatever I wish to do.</li></ul><br>"
                + "-----------------------------------------------------------------------------------------<br><b><u>Academic Details</u></b><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>School</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<b>College</b><br/>"
                + "Name:" + strschool + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name:" + strclgnm + "<br/>"
                + "Percentage(In 12th):" + strper + "%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CGPA:" + strcgpa
                + "<br/>Stream:" + strstream + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Course:" + strcourse
                + "<br/>-----------------------------------------------------------------------------------------<br/><b><u>Project</u></b><br/>Name:" + strprojname + "<br/>Description:" + strprojdesc + "<br/>Company Name:" + strcomname + "<br/>"
                + "-----------------------------------------------------------------------------------------<br/><b><u>Profession(s):</u></b>" + strprofessionname + "<br/>"
                + "-----------------------------------------------------------------------------------------<br/><b><u>Skill(s):</u></b>" + strskill + "<br/>"
                + "-----------------------------------------------------------------------------------------<br/><b><u>Interest(s):</u></b>" + strinterest + "<br/>"
                + "-----------------------------------------------------------------------------------------<br/><b><u>Experience</u></b><br/>"
                + "Experience(in professional field)in years:" + strexp + "<br/>Work:" + strplace + "<br/>" + "Designation:" + strdesign + "<br/>"

        ));

    }

    @Override
    public void onClick(View v) {
        sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
        edl = sl.edit();
        edl.putString("value", "1");
        edl.commit();
        String val = sl.getString("value", "no such activity");
        Log.d("abcde", val);
        Intent i = new Intent(FinalActivityDisplay.this, HomeScreen.class);
        startActivity(i);

    }
}
