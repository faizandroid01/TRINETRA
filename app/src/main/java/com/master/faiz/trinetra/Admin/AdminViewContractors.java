package com.master.faiz.trinetra.Admin;

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

public class AdminViewContractors extends AppCompatActivity {

    Toolbar toolbar;
    ListView contractor_listview;
    ArrayAdapter<String> adapter;
    private ProgressDialog progressDialog;
    ArrayList<String> contract_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_contractors);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("CONTRACTORS");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching contractor List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        contract_list = new ArrayList<>();

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

       /* final String items[] = new String[contract_list.size()];

           for (int i = 0; i < contract_list.size(); i++) {
            items[i] = contract_list.get(i);

        }*/


        final String[] items = {"C 1", "C 2", "C 3", "C 4"};
        contractor_listview = (ListView) findViewById(R.id.contractor_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        contractor_listview.setAdapter(adapter);

        contractor_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String contractor_name = items[position];
                Intent intent = new Intent(AdminViewContractors.this, AdminViewContractorDetails.class);
                intent.putExtra("contractor_name", contractor_name);
                startActivity(intent);
            }
        });
    }

    public void adminAddContractor(View view) {
        startActivity(new Intent(this, AdminAddContractor.class));
    }
}