package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.master.faiz.trinetra.R;

public class AdminAddProject extends AppCompatActivity {

    Toolbar toolbar;
    String admin_project_name;
    EditText adminProjectName, adminProjectStrtDate, adminProjectEndDate, adminProjectLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_project);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD PROJECT");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        adminProjectName = (EditText) findViewById(R.id.activity_admin_add_project_project_name);
        adminProjectStrtDate = (EditText) findViewById(R.id.activity_admin_add_project_project_start_date);
        adminProjectEndDate = (EditText) findViewById(R.id.activity_admin_add_project_project_end_date);
        adminProjectLocation = (EditText) findViewById(R.id.activity_admin_add_project_project_location);



        Bundle b =getIntent().getExtras();
        if(b!=null) {
            admin_project_name = b.getString("admin_project_name");

        // fetch the details from data base regarding "admin_project_name" and show it in fields available ,, except the PID , which is auto generated ..


        }


    }

    public void saveProjectDetails(View view) {

        startActivity(new Intent(this,AdminProject.class));


        /*
        * 0657 228888   City Clinic ---
        * tue and friday -- 8 am to 11 30 am ----  500  10   ---------
        * */

    }
}
