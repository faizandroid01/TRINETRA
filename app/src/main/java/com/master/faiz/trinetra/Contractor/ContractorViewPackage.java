package com.master.faiz.trinetra.Contractor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.ArrayList;

import Utils.VolleySingleton;

public class ContractorViewPackage extends AppCompatActivity {

    Toolbar toolbar;
    ListView contractor_package_listView;
    ArrayAdapter<String> adapter;
    String package_name;
    String project_name;
    LinearLayout linearLayout;
    private ProgressDialog progressDialog;
    ArrayList<String> cont_package_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_view_package);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            project_name = b.getString("contractor_project_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(project_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));
        linearLayout = (LinearLayout) findViewById(R.id.activity_contractor_view_package_linear_layout);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching package List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        cont_package_list = new ArrayList<>();

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

       /* final String items[] = new String[cont_package_list.size()];

        for (int i = 0; i < cont_package_list.size(); i++) {

            items[i] = cont_package_list.get(i);

        }*/


        final String items[] = {"cpk 1", "cpk 2", "cpk 3", "cpk 4"};
        contractor_package_listView = (ListView) findViewById(R.id.activity_contractor_view_package_package_listview);

        adapter = new ArrayAdapter<String>(ContractorViewPackage.this, android.R.layout.simple_dropdown_item_1line, items);
        contractor_package_listView.setAdapter(adapter);

        contractor_package_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                package_name = items[position];

                Intent i = new Intent(ContractorViewPackage.this, ContractorAssignWorker.class);
                i.putExtra("contractor_package_name", package_name);
                startActivity(i);
            }
        });

        contractor_package_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                package_name = items[position];

                new AlertDialog.Builder(ContractorViewPackage.this)
                        .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //Delete from database

                            }
                        }).setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                }).setCancelable(false)
                        .create().show();


                return true;
            }
        });

    }
}
