package com.master.faiz.trinetra.Contractor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.master.faiz.trinetra.R;

public class ContractorAssignPackageWorker extends AppCompatActivity {


    Toolbar toolbar;
    String contractor_package_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_assignpackage_worker);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            contractor_package_name = b.getString("contractor_package_name");
        }


        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_package_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));





    }

    public void savePackageWiseWorkerDetail(View view) {
        Toast.makeText(this, "Display employee in checkboxes and save to database", Toast.LENGTH_SHORT).show();
    }
}
