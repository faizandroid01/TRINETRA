package com.master.faiz.trinetra.Contractor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.faiz.trinetra.R;

public class ContractorAssignWorker extends AppCompatActivity {

    Toolbar toolbar;
    String contractor_package_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_assign_worker);

        Bundle b = getIntent().getExtras();
        if (b != null) {
           contractor_package_name = b.getString("contractor_package_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_package_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));




    }

    public void contractorAssignPackageWorker(View view) {
        Intent i = new Intent(ContractorAssignWorker.this,ContractorAssignPackageWorker.class);
        i.putExtra("contractor_package_name",contractor_package_name);
        startActivity(i);

    }

    public void contractorViewPackageWorker(View view) {

        Intent i = new Intent(ContractorAssignWorker.this,ContractorViewPackageWorker.class);
        i.putExtra("contractor_package_name",contractor_package_name);
        startActivity(i);

    }

    public void contractorAssignSupervisor(View view) {
        Intent i = new Intent(ContractorAssignWorker.this, ContractorAssignSupervisor.class);
        i.putExtra("contractor_package_name", contractor_package_name);
        startActivity(i);
    }
}
