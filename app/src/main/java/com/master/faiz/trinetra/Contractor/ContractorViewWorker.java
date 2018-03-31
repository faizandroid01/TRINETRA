package com.master.faiz.trinetra.Contractor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class ContractorViewWorker extends AppCompatActivity {

    Toolbar toolbar;
    ListView contractor_worker_listView;
    ArrayAdapter<String> adapter;
    String worker_name;
    private ProgressDialog progressDialog;
    ArrayList<String> wrkr_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_view_worker);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("WORKER LIST");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching package wrkr List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        wrkr_list = new ArrayList<>();

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

       /* final String items[] = new String[wrkr_list.size()];

        for (int i = 0; i < wrkr_list.size(); i++) {

            items[i] = wrkr_list.get(i);

        }*/


        final String items[] = {"W1", "W2", "W3", "W4"};
        contractor_worker_listView = (ListView) findViewById(R.id.contractor_worker_listview);
        adapter = new ArrayAdapter<String>(ContractorViewWorker.this, android.R.layout.simple_dropdown_item_1line, items);
        contractor_worker_listView.setAdapter(adapter);

        contractor_worker_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                worker_name = items[position];
                Intent i = new Intent(ContractorViewWorker.this, ContractorAddWorker.class);
                // If want to change then change ,, I am sending the user back to Contactor Add worker panel to see the data he entered ,, there it the data
                // is needed to be fetched corresponding to contractor_name .
                i.putExtra("worker_name", worker_name);
                startActivity(i);

            }
        });


        contractor_worker_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                worker_name = items[position];


                new AlertDialog.Builder(ContractorViewWorker.this)
                        .setTitle("options")
                        .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Delete from data base
                            }
                        })
                        .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(ContractorViewWorker.this, ContractorAddWorker.class);
                        i.putExtra("worker_name", worker_name);
                        startActivity(i);


                    }
                }).create().show();

                return true;
            }
        });

    }
}
