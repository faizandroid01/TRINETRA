package com.master.faiz.trinetra.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class AdminProject extends AppCompatActivity {

    Toolbar toolbar;
    ListView project_listView;
    ArrayAdapter<String> adapter;
    String admin_project_name;
    TextView adminName;
    ArrayList<String> project_name_list;
    ArrayList<String> project_id_list;
    private ProgressDialog progressDialog;
    String bundle_adminName;
    String bundle_project_id;
    LinearLayout linearLayout;
    String fetched_project_name;
    String fetched_project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_project);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(R.string.admin_projects);
        adminName = (TextView) findViewById(R.id.activity_admin_project_admin_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_project_linear_layout);

        // @receive values from Login Activity ..

        Bundle b = getIntent().getExtras();
        if (b != null) {

            bundle_project_id = b.getString("user_id");
            bundle_adminName = b.getString("user_name");
            b.get("user_aadhar");
            b.get("user_type");
        }

        // @assign bundle stuffs

        adminName.setText(bundle_adminName);

        //@ dialog progress stuffs ..
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching project List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        project_name_list = new ArrayList<>();
        project_id_list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(AdminProject.this, "" + response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                // parse the response and assign it to ArrayList package_list and then assign array list items to items []  ..
                // @GET Method --  fetch the adminName and his projects corresponding to project id  .

                          /*  Example Json feed
                    * {
                    *
                    * {
                    * status : -1
                    * }
                    *
                    *      project_id, {
                    *
                    *           project_name,
                    *           project_location,
                    *           project_start_date,
                    *           project_end_dat
                    *           },
                    *
                    *            *      project_id_@, {
                    *
                    *           project_name,
                    *           project_location,
                    *           project_start_date,
                    *           project_end_dat
                    *           },
                    *
                    *
                    * */


                try {
                    JSONObject res = new JSONObject(response);

                    if (res.has("status")) {
                        Snackbar.make(linearLayout, "No current Projects available", Snackbar.LENGTH_SHORT).show();
                    } /*else {

                        JSONArray jsonArray = new JSONArray(response);
                        Log.i("line no 138", "Converted to jsonArray");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            fetched_project_id = jsonObject.toString();
                            project_id_list.add(fetched_project_id);
                            Log.i("line no 141", "Added");
                            fetched_project_name = jsonObject.getString("project_name");
                            project_name_list.add(fetched_project_name);
                            jsonObject.getString("project_location");
                            jsonObject.getString("project_start_date");
                            jsonObject.getString("project_end_date");

                        }*/

                    //  }
                } catch (JSONException e) {
                    Log.e("line 157", "JSON EX");
                }


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

        final String items[] = new String[project_name_list.size()];

        for (int i = 0; i < project_name_list.size(); i++) {

            items[i] = project_name_list.get(i);
            adapter.notifyDataSetChanged();


        }

        //@Assign After getting the projects list in response,, Assign it to the items array below to display it in listview
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
        Intent i = new Intent(this, AdminAddProject.class);
        i.putExtra("user_id", bundle_project_id);
        startActivity(i);
    }
}


