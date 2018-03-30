package com.master.faiz.trinetra.Contractor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.master.faiz.trinetra.R;

public class ContractorAssignSupervisor extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_assign_supervisor);


        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ASSIGN SUPERVISOR");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

    }
}
