package com.master.faiz.trinetra.Supervisor;

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

public class SupervisorLogin extends AppCompatActivity {

    Toolbar toolbar;
    ListView sup_project_listView;
    String project_name;
    private ProgressDialog progressDialog;
    ArrayList<String> sup_project_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_login);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching package wrkr List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        sup_project_list = new ArrayList<>();

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

       /* final String items[] = new String[sup_project_list.size()];

        for (int i = 0; i < sup_project_list.size(); i++) {

            items[i] = sup_project_list.get(i);

        }*/


        sup_project_listView = (ListView) findViewById(R.id.supervisor_project_listview);
        final String []items = {"P1","P2","P3","P4"};

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("PROJECTS");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        ArrayAdapter<String> aa= new ArrayAdapter<String>(this , android.R.layout.simple_dropdown_item_1line , items);
        sup_project_listView.setAdapter(aa);

        sup_project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                project_name = items[position];
                Intent intent = new Intent(SupervisorLogin.this,SupervisorPackageDetails.class );
                intent.putExtra("project_name",project_name);
                startActivity(intent);

            }
        });

    }
}
