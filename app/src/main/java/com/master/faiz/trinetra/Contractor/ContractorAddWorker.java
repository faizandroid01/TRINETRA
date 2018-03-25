package com.master.faiz.trinetra.Contractor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.master.faiz.trinetra.R;

public class ContractorAddWorker extends AppCompatActivity {
    Toolbar toolbar;

    ArrayAdapter<String> aa;
    Spinner cont_worker_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_add_worker);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            String worker_name = b.getString("worker_name");

            Toast.makeText(this, worker_name, Toast.LENGTH_SHORT).show();
            // fetch the data and assign to respective editext
        }
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD WORKKER");

        String items[] = {"SKILLED/TEMPORARY", "SKILLED/PERMANENT", "SKILLED/DAILY WAGE", "SEMI-SKILLED/TEMPORARY", "SEMI-SKILLED/PERMANENT", "SEMI-SKILLED/DAILY WAGE", "UNSKILLED/TEMPORARY", "UNSKILLED/PERNANENT", "UNSKILLED/DAILY WAGE"};
        cont_worker_type = (Spinner) findViewById(R.id.activity_contractor_add_worker_worker_type_spinner);
        aa = new ArrayAdapter<String>(ContractorAddWorker.this, android.R.layout.simple_dropdown_item_1line, items);
        cont_worker_type.setAdapter(aa);


    }

    public void addWorker(View view) {

        //ADD the data from the above field into the data base  ..
        // And if it is already updated its needed to be updated
    }
}
