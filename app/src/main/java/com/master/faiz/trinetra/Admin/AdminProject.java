package com.master.faiz.trinetra.Admin;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.ArrayList;

import Utils.VolleySingleton;

public class AdminProject extends AppCompatActivity {

    Toolbar toolbar;
    ListView project_listView;
    ArrayAdapter<String> adapter;
    String admin_project_name;
    TextView adminName;
    ArrayList<String> project_list;
    private ProgressDialog progressDialog;
    String bundle_adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_project);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(R.string.admin_projects);
        adminName = (TextView) findViewById(R.id.activity_admin_project_admin_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        // @receive values from Login Activity ..

        Bundle b = getIntent().getExtras();
        if (b != null) {

            b.get("user_email");
            b.get("user_type");
            b.get("user_aadhar");
            bundle_adminName = b.getString("user_name");
        }

        // @assign bundle stuffs

        adminName.setText(bundle_adminName);

        //@ dialog progress stuffs ..
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching project List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        project_list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                // parse the response and assign it to ArrayList package_list and then assign array list items to items []  ..
                // @GET Method --  fetch the adminName and his projects corresponding to project id  .

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

       /* final String items[] = new String[project_list.size()];

        for (int i = 0; i < project_list.size(); i++) {

            items[i] = project_list.get(i);

        }*/


        //@Assign After getting the projects list in response,, Assign it to the items array below to display it in listview

        final String[] items = {"P1", "P2", "P3", "P4"};
        project_listView = (ListView) findViewById(R.id.admin_project_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        project_listView.setAdapter(adapter);

        project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                admin_project_name = items[position];
                Intent intent = new Intent(AdminProject.this, AdminPackages.class);
                //   intent.putExtra("admin_project_name", admin_project_name);
                startActivity(intent);

            }
        });


        project_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(AdminProject.this)
                        .setTitle("Options")
                        .setMessage("Choose Options for Project .")
                        .setIcon(android.R.drawable.dark_header)
                        .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(AdminProject.this, "Delete from Database .", Toast.LENGTH_SHORT).show();

                            }
                        }).setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        admin_project_name = items[position];
                        Intent intent = new Intent(AdminProject.this, AdminAddProject.class);
                        intent.putExtra("admin_project_name", admin_project_name);
                        startActivity(intent);


                    }
                }).create()
                        .show();


                return true;
            }
        });


    }

    public void adminAddProject(View view) {

        startActivity(new Intent(this, AdminAddProject.class));
    }
}


