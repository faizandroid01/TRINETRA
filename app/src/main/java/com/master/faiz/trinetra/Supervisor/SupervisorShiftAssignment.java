package com.master.faiz.trinetra.Supervisor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.master.faiz.trinetra.R;

public class SupervisorShiftAssignment extends AppCompatActivity {

    Toolbar toolbar ;
    Spinner spinner ;
    TextView number;
    String package_name;
    String cbText[];
    int cbId[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_shift_assignment);
        spinner = (Spinner) findViewById(R.id.activity_supervisor_shift_assignment_shift_spinner);
        String items[] = {"     A       ","     B       ","     C       "};

        Bundle b=getIntent().getExtras();
        if(b!=null){
            package_name =  b.getString("package_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(package_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        number = (TextView)findViewById(R.id.textview2);
     /*
         cbText = new String[]{"c1", "c2","c1", "c2","c1", "c2","c1", "c2","c1", "c2","c1", "c2"};
        cbId = new Integer[]{cbId1, cbId2,cbId1, cbId2,cbId1, cbId2,cbId1, cbId2,cbId1, cbId2,cbId1, cbId2};*/

        ArrayAdapter<String > aa= new ArrayAdapter<String>(SupervisorShiftAssignment.this,android.R.layout.simple_dropdown_item_1line,items);
        spinner.setAdapter(aa);



    }

    public void startRandomFunction(View view) {


    }

 /*   public void checkBoxCreation(View view) {

        String x =number.getText().toString();
        int count = Integer.parseInt(x);
        int i;

        for (i=0; i< count ; i++)
        {

            CheckBox cb= new CheckBox(SupervisorShiftAssignment.this);
            cb.setText(cbText[i]);
            cb.setId(cbId[i]);


        }

    }*/
}
