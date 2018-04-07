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
import android.widget.ListView;
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

import HelperClass.AdminProjectWisePackageListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeAdminProjectWisePackageListAdapter;

public class AdminPackages extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    private ProgressDialog progressDialog;
    ArrayList<String> package_id_list;

    String fetched_package_name;
    String fetched_package_id;
    String fetched_package_location;

    String selected_project_name;
    String selected_project_id;

    String selected_package_name;
    String selected_package_id;

    private SwipeRefreshLayout swipeRefreshLayout;
    String bundle_user_id;

    private ListView package_listView;
    private SwipeAdminProjectWisePackageListAdapter adapter;
    private List<AdminProjectWisePackageListViewItems> adminPackageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_packages);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);

        package_listView = (ListView) findViewById(R.id.admin_package_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_package_panel);
        adminPackageList = new ArrayList<>();
        package_id_list = new ArrayList<>();


        Bundle b = getIntent().getExtras();
        if (b != null) {

            selected_project_name = b.getString("selected_project_name");
            selected_project_id = b.getString("selected_project_id");
            bundle_user_id = b.getString("user_id");

            Log.i("selected_project_name", selected_project_name);
            Log.i("selected_project_id", selected_project_id);
            toolbar.setTitle(selected_project_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        }


        adapter = new SwipeAdminProjectWisePackageListAdapter(this, adminPackageList);
        package_listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetch_data();
                                    }
                                }
        );


        startProgressDialog();


        package_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AdminPackages.this, AdminViewPackage.class);
                //  intent.putExtra("admin_package_name", admin_package_name);
                startActivity(intent);


            }
        });


        package_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(AdminPackages.this)
                        .setTitle("Options")
                        .setMessage("Choose Options for Project .")
                        .setIcon(android.R.drawable.dark_header)
                        .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(AdminPackages.this, "Delete from Database .", Toast.LENGTH_SHORT).show();

                            }
                        }).setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(AdminPackages.this, AdminAddPackage.class);
                        //    intent.putExtra("admin_package_name", admin_package_name);
                        startActivity(intent);
                    }
                }).create()
                        .show();
                return true;
            }
        });
    }

    public void fetch_data() {

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
                        Toast.makeText(AdminPackages.this, "No current Packages available", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject packages_details = new JSONObject(response);

                        JSONArray package_ids_array = packages_details.names();
                        for (int i = 0; i < package_ids_array.length(); i++) {

                            JSONObject project_details = packages_details.getJSONObject(package_ids_array.get(i).toString());
                            //  Log.i("project_details", project_details.toString());
                            fetched_package_name = (String) project_details.get("package_name");
                            //  Log.e("project_name", (String) project_details.get("package_name"));
                            fetched_package_id = package_ids_array.get(i).toString();
                            package_id_list.add(0, fetched_package_id);
                            fetched_package_location = project_details.getString("package_location");
                            //after modification

                            AdminProjectWisePackageListViewItems a_packages = new AdminProjectWisePackageListViewItems(fetched_package_name, fetched_package_location);
                            adminPackageList.add(0, a_packages);

                        }

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.e("Admin Packages", "JSON EXCEPTION");
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

                params.put("project_id", selected_project_id);

                params.put("module", "package");
                params.put("query_type", DataWrapper.QTYPE_O);
                params.put("query", "read_package");


                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    public void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching project List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    public void adminAddPackage(View view) {

        Intent i = new Intent(this, AdminAddPackage.class);
        i.putExtra("user_id", bundle_user_id);
        i.putExtra("selected_project_name", selected_project_name);
        i.putExtra("selected_project_id", selected_project_id);

        startActivity(i);
    }

    @Override
    public void onRefresh() {
        fetch_data();
    }
}
