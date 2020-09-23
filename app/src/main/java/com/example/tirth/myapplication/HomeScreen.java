package com.example.tirth.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mdrawerLayout = null;
    private ActionBarDrawerToggle mtbtn = null;
    Intent inten = null;
    AlertDialog al;
    EditText usrusernm;
    EditText usrpass;
    String inbuiltUsr;
    String inbuiltPass;
    SharedPreferences s;
    SharedPreferences.Editor sh;
    static int cnt = 0;
    SQLiteDatabase db;
    Button btnLogRef;
    public static int refId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mtbtn = new ActionBarDrawerToggle(HomeScreen.this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mtbtn);
        mtbtn.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = openOrCreateDatabase("Cvmaker", MODE_PRIVATE, null);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        if (cnt == 0) {
//            SharedPreferences sl;
//            SharedPreferences.Editor edl;
//            sl = getSharedPreferences("LatestActivity", MODE_PRIVATE);
//            edl = sl.edit();
//            edl.putString("value", "1");
//            edl.commit();
//            cnt++;
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtbtn.onOptionsItemSelected(item)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Sign: {
                inten = new Intent(this, MainActivity.class);
                startActivity(inten);
                return true;
            }
            case R.id.Login: {
                AlertDialog.Builder build = new AlertDialog.Builder(HomeScreen.this);
                View vl = getLayoutInflater().inflate(R.layout.login_dialog, null);
                usrusernm = (EditText) vl.findViewById(R.id.edusr);
                usrpass = (EditText) vl.findViewById(R.id.edpass);
                btnLogRef = (Button) vl.findViewById(R.id.btnlogin);

                btnLogRef.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(HomeScreen.this, "bahar", Toast.LENGTH_SHORT).show();
                        String enteredUsr = usrusernm.getText().toString();
                        String enteredPass = usrpass.getText().toString();
                        Cursor c = db.rawQuery("select * from SignUp", null);
                        c.moveToFirst();
                        do {
                            //    Toast.makeText(HomeScreen.this, "andar", Toast.LENGTH_SHORT).show();
                            String usrnm = c.getString(c.getColumnIndex("username"));
                            String pass = c.getString(c.getColumnIndex("password"));
                            if (enteredUsr.equals(usrnm) && enteredPass.equals(pass)) {
                                refId = c.getInt(c.getColumnIndex("userid"));

                                        s = getSharedPreferences("refid", MODE_PRIVATE);
                                        sh = s.edit();
                                        sh.putString("val", refId + "");
                                        sh.commit();
                                     //   Toast.makeText(HomeScreen.this, "Correct", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(HomeScreen.this, PersonalInformation.class);
                                        startActivity(i);
                            }
                        } while (c.moveToNext());

                    }
                });
                build.setView(vl);
                al = build.create();
                al.setCancelable(true);
                al.show();

                return true;
            }
        }
        return false;
    }
}