package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.master.faiz.trinetra.R;

public class AdminAddProject extends AppCompatActivity {

    Toolbar toolbar;
    String admin_project_name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_project);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD PROJECT");

        Bundle b =getIntent().getExtras();
        if(b!=null) {
            admin_project_name = b.getString("admin_project_name");

        // fetch the details from data base regarding "admin_project_name" and show it in fields available ,, except the PID , which is auto generated ..


        }


    }

    public void saveProjectDetails(View view) {

        startActivity(new Intent(this,AdminProject.class));

    }
}
