package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.master.faiz.trinetra.R;

public class AdminAddPackage extends AppCompatActivity {


    Toolbar toolbar ;
    String admin_package_name;
    EditText packageName, packageStrtDate, packageEndDate, packageLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_package);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD PACKAGE");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        packageName = (EditText) findViewById(R.id.activity_admin_add_package_package_name);
        packageStrtDate = (EditText) findViewById(R.id.activity_admin_add_package_package_start_date);
        packageEndDate = (EditText) findViewById(R.id.activity_admin_add_package_package_end_date);
        packageLocation = (EditText) findViewById(R.id.activity_admin_add_package_package_location);


        Bundle b = getIntent().getExtras();
        if(b!=null) {
            admin_package_name = b.getString("admin_package_name");

            // fetch the details from data base regarding "admin_project_name" and show it in fields available ,, except the PID , which is auto generated ..


        }

    }

    public void savePackageDetails(View view) {
        startActivity(new Intent(this, AdminPackages.class));
    }
}
