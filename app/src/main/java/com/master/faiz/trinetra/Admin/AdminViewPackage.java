package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.faiz.trinetra.R;

public class AdminViewPackage extends AppCompatActivity {

    Toolbar toolbar;
    String admin_package_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_package);

        Bundle b =getIntent().getExtras();
        if(b!=null)
         admin_package_name = b.getString("admin_package_name");

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(admin_package_name);


    }

    public void viewContractors(View v)
    {
        startActivity(new Intent(this,AdminViewContractors.class ));
    }
    public void packageReport(View v)
    {
        startActivity(new Intent(AdminViewPackage.this,AdminProjectReport.class));
    }

}
