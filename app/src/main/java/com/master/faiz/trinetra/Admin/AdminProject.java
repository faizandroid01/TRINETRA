package com.master.faiz.trinetra.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HelperClass.AdminProjectListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeAdminProjectListAdapter;

public class AdminProject extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    String admin_project_name;
    TextView adminName;
    ArrayList<String> project_name_list;
    ArrayList<String> project_id_list;
    private ProgressDialog progressDialog;
    String bundle_adminName;
    String bundle_user_id;
    LinearLayout linearLayout;

    String fetched_project_name;
    String fetched_project_id;
    String fetched_project_location;

  /*  String selected_project_id ;
    String selected_project_name;
*/

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView project_listView;
    private SwipeAdminProjectListAdapter adapter;
    private List<AdminProjectListViewItems> adminProjectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_project);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(R.string.admin_projects);
        adminName = (TextView) findViewById(R.id.activity_admin_project_admin_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_project_linear_layout);
        project_name_list = new ArrayList<>();
        project_id_list = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        swipeRefreshLayout.setOnRefreshListener(this);


        //after modifications

        project_listView = (ListView) findViewById(R.id.admin_project_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        adminProjectList = new ArrayList<>();
        adapter = new SwipeAdminProjectListAdapter(this, adminProjectList);
        project_listView.setAdapter(adapter);

        Toast.makeText(this, "on create", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetch_data();
                                    }
                                }
        );


        // @receive values from Login Activity ..

        Bundle b = getIntent().getExtras();

        if (b != null) {

            bundle_user_id = b.getString("user_id");
            bundle_adminName = b.getString("user_name");
            b.get("user_aadhar");
            b.get("user_type");
        }

        // @assign bundle stuffs

        adminName.setText(bundle_adminName);

        startProgressDialog();

        project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(AdminProject.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminProject.this, AdminPackages.class);
                intent.putExtra("selected_project_name", project_name_list.get(position));
                Log.i("project_name", project_name_list.get(position));
                intent.putExtra("selected_project_id", project_id_list.get(position));
                Log.i("project_name", project_id_list.get(position));
                intent.putExtra("user_id", bundle_user_id);
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


    @Override
    protected void onResume() {
        Toast.makeText(this, "on Resume", Toast.LENGTH_SHORT).show();


        super.onResume();
    }

    public void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching project List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    public void adminAddProject(View view) {
        Intent i = new Intent(this, AdminAddProject.class);
        i.putExtra("user_id", bundle_user_id);
        i.putExtra("user_name", bundle_adminName);
        startActivity(i);
    }


    public void fetch_data() {

        swipeRefreshLayout.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                // parse the response and assign it to ArrayList package_list and then assign array list items to items []  ..
                // @GET Method --  fetch the adminName and his projects corresponding to project id  .


                Log.i("reponse came", response);
                progressDialog.dismiss();


                try {
                    JSONObject xx = new JSONObject(response);
                    if (xx.has("status")) {
                        Toast.makeText(AdminProject.this, "No current Projects available", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject projects_details = new JSONObject(response);

                        JSONArray project_ids_array = projects_details.names();
                        for (int i = 0; i < project_ids_array.length(); i++) {

                            JSONObject project_details = projects_details.getJSONObject(project_ids_array.get(i).toString());
                            Log.i("project_details", project_details.toString());
                            fetched_project_name = (String) project_details.get("project_name");
                            project_name_list.add(0, fetched_project_name);
                            Log.e("project_name", (String) project_details.get("project_name"));
                            fetched_project_id = project_ids_array.get(i).toString();
                            project_id_list.add(0, fetched_project_id);
                            fetched_project_location = project_details.getString("project_location");
                            //after modification

                            AdminProjectListViewItems adminProjectListViewItems = new AdminProjectListViewItems(fetched_project_name, fetched_project_location);
                            adminProjectList.add(0, adminProjectListViewItems);

                        }

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.e("lne no 173", "JSON EXCEPTION");
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Log.i("Error: ", error.getMessage());
                swipeRefreshLayout.setRefreshing(false);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("user_added_id", bundle_user_id);

                params.put("module", "project");
                params.put("query_type", DataWrapper.QTYPE_O);
                params.put("query", "read_projects");


                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onRefresh() {
        fetch_data();

    }

}

