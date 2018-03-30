package com.master.faiz.trinetra.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.master.faiz.trinetra.R;

public class AdminViewProjectReport extends AppCompatActivity {

    Toolbar toolbar;
    TextView noOfWorkerpresent, authGenerated, successfullAuth, overallPercentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_project_report);

        Bundle b = getIntent().getExtras();
        String contractor_name = b.getString("contractor_name");
        String date = b.getString("date");
        String month = b.getString("month");
        String year = b.getString("year");

        String requiredDate = date + "/" + month + "/" + year;

        noOfWorkerpresent = (TextView) findViewById(R.id.activity_admin_view_project_report_worker_present);
        authGenerated = (TextView) findViewById(R.id.activity_admin_view_project_report_authentication_generated);
        successfullAuth = (TextView) findViewById(R.id.activity_admin_view_project_report_successfull_authentication);
        overallPercentage = (TextView) findViewById(R.id.activity_admin_view_project_report_overall_record);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


    }
}
