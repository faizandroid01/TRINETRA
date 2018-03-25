package com.master.faiz.trinetra.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.master.faiz.trinetra.R;

public class AdminViewProjectReport extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_project_report);

        Bundle b =getIntent().getExtras();
        String contractor_name = b.getString("contractor_name");
        String date = b.getString("date");
        String month =b.getString("month");
        String  year = b.getString("year");

        Toast.makeText(this, ""+date+":"+month+":"+year, Toast.LENGTH_SHORT).show();

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);


    }
}
