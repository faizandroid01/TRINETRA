package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.master.faiz.trinetra.R;

public class AdminViewContractorDetails extends AppCompatActivity {

    Toolbar toolbar ;

    TextView contractorName, nOSkilled, nOSemiSkilled, permanentStatus, temporaryStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_contractor_details);

        Bundle b =getIntent().getExtras();
        String contractor_name = b.getString("contractor_name");

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        contractorName = (TextView) findViewById(R.id.activity_admin_view_contractor_details_contractor_name);
        nOSkilled = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_skilled_workers);
        nOSemiSkilled = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_semiskilled_workers);
        permanentStatus = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_permanent_workers);
        temporaryStatus = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_temporary_workers);





    }

    public void scheduleAttendance(View v){

        startActivity(new Intent(this,AdminScheduleAttendance.class));

    }
}
