package com.example.tirth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {

    ImageView iv;
    SharedPreferences p;
    SharedPreferences.Editor ped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_splashscreen);
        iv = (ImageView) findViewById(R.id.iv);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.tweenanim);
        iv.startAnimation(a);

        Thread t = new Thread() {


            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SharedPreferences preferences = getSharedPreferences("myPref", MODE_PRIVATE);
                    boolean shouldInsertData = preferences.getBoolean("shouldInsertData", true);

                    //insert your data into the preferences
                    if(shouldInsertData){

                        //insert your data into the preferences
                        p = getSharedPreferences("LatestActivity", MODE_PRIVATE);
                    ped = p.edit();
                    ped.putString("value", 1 + "");
                    ped.commit();
                        preferences.edit().putBoolean("shouldInsertData", false).apply();

                    }



//                    p = getSharedPreferences("LatestActivity", MODE_PRIVATE);
//                    ped = p.edit();
//                    ped.putString("value", 1 + "");
//                    ped.commit();
                    //   preferences.edit().putBoolean("shouldInsertData", false).apply();


                    p = getSharedPreferences("LatestActivity", MODE_PRIVATE);
                    int act = Integer.parseInt(p.getString("value", "no such activity"));
                    Log.d("abcde", "" + act);
                    Intent i;

                    switch (act) {

                        case 2:
                            i = new Intent(Splashscreen.this, PersonalInformation.class);
                            startActivity(i);
                            break;
                        case 3:
                            i = new Intent(Splashscreen.this, EducationDetails.class);
                            startActivity(i);
                            break;
                        case 4:
                            i = new Intent(Splashscreen.this, SelectProfession.class);
                            startActivity(i);
                            break;
                        case 5:
                            i = new Intent(Splashscreen.this, SkillActivity.class);
                            startActivity(i);
                            break;
                        case 6:
                            i = new Intent(Splashscreen.this, IntrestActivity.class);
                            startActivity(i);
                            break;
                        case 7:
                            i = new Intent(Splashscreen.this, Experience.class);
                            startActivity(i);
                            break;
                        case 8:
                            i = new Intent(Splashscreen.this, Project.class);
                            startActivity(i);
                            break;
                        case 9:
                            i = new Intent(Splashscreen.this, Reference.class);
                            startActivity(i);
                            break;
                        case 10:
                            i = new Intent(Splashscreen.this, FinalActivityDisplay.class);
                            startActivity(i);
                            break;

                        default:
                            i = new Intent(Splashscreen.this, HomeScreen.class);
                            startActivity(i);
                            break;


                    }
                }

            }
        };
        t.start();


    }

}

