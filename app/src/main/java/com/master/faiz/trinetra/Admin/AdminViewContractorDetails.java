package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.faiz.trinetra.R;

public class AdminViewContractorDetails extends AppCompatActivity {

    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_contractor_details);

        Bundle b =getIntent().getExtras();
        String contractor_name = b.getString("contractor_name");

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);




    }

    public void scheduleAttendance(View v){

        startActivity(new Intent(this,AdminScheduleAttendance.class));

    }
}
