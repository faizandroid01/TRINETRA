package com.master.faiz.trinetra.Contractor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.ArrayList;

import Utils.VolleySingleton;

public class ContractorViewPackageWorker extends AppCompatActivity {

    Toolbar toolbar;
    String contractor_package_name;
    ListView package_worker;
    ArrayAdapter<String> aa;
    String worker_name ;
    private ProgressDialog progressDialog;
    ArrayList<String> package_wrkr_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_view_package_worker);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            contractor_package_name = b.getString("contractor_package_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_package_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching package wrkr List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        package_wrkr_list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                progressDialog.dismiss();
                // parse the response and assign it to ArrayList package_list and then assign array list items to items []  ..

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Log.i("Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog


            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

       /* final String items[] = new String[package_wrkr_list.size()];

        for (int i = 0; i < package_wrkr_list.size(); i++) {

            items[i] = package_wrkr_list.get(i);

        }*/

        final String items[] = {"pkwrkr 1 ", "pkwrkr 2", "pkwrkr 3", "pkwrkr 4"};
        package_worker = (ListView) findViewById(R.id.contractor_package_worker_listview);

        aa = new ArrayAdapter<String>(ContractorViewPackageWorker.this, android.R.layout.simple_dropdown_item_1line, items);
        package_worker.setAdapter(aa);

        package_worker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                worker_name = items[position];
                Intent i = new Intent(ContractorViewPackageWorker.this, ContractorAddWorker.class);
                // If want to change then change ,, I am sending the user back to Contactor Add worker panel to see the data he entered ,, there it the data
                // is needed to be fetched corresponding to contractor_name .
                i.putExtra("worker_name", worker_name);
                startActivity(i);

            }
        });

    }
}
