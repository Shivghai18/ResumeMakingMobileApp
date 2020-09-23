package com.example.tirth.myapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


/**
 * Created by Doctor7 on 13-10-2017.
 */

public class CustomDialog extends DialogFragment {
    private RadioButton rby, rbn;
    private Button btnsub;
    private RadioGroup rbg;
    private Boolean Q;

    public Boolean getQ() {
        return Q;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.customdialogbox, container);
        rby = (RadioButton) v.findViewById(R.id.rbyes);
        rbn = (RadioButton) v.findViewById(R.id.rbno);
        rbg = (RadioGroup) v.findViewById(R.id.Rbg);


        btnsub = (Button) v.findViewById(R.id.btnsub);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbg.getCheckedRadioButtonId() != -1) {
                    int id = rbg.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) rbg.findViewById(id);
//                    int radioId = rbg.indexOfChild(radioButton);
//                    RadioButton btn = (RadioButton) rbg.getChildAt(radioId);
                    String str = (String) radioButton.getText();
                    Log.d("DebraMorgan1", str);
                    if (str.equals("Yes")) {
                        Q = true;

                    } else if (str.equals("No")) {
                        Q = false;

                    } else {
                        Q = null;
                    }
                }
                dismiss();
            }
        });

        return v;

    }


}
