package com.master.faiz.trinetra.Supervisor;

import android.app.ProgressDialog;
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

import HelperClass.SupervisorProjectWisePackageListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeSupervisorProjectWisePackageListAdapter;

public class SupervisorPackageDetails extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    Toolbar toolbar;
    String package_name;
    String project_name;
    private ProgressDialog progressDialog;
    ArrayList<String> sup_package_id_list;


    String fetched_sup_package_name;
    String fetched_sup_package_id;
    String fetched_sup_package_location;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView sup_package_listView;
    private SwipeSupervisorProjectWisePackageListAdapter adapter;
    private List<SupervisorProjectWisePackageListViewItems> supervisorPackageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_package_details);

        sup_package_listView = (ListView) findViewById(R.id.activity_supervisor_login_package_listview);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            project_name = b.getString("project_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(project_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        sup_package_listView = (ListView) findViewById(R.id.activity_supervisor_login_package_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        supervisorPackageList = new ArrayList<>();
        adapter = new SwipeSupervisorProjectWisePackageListAdapter(this, supervisorPackageList);
        sup_package_listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetch_data();
                                    }
                                }
        );

        sup_package_id_list = new ArrayList<>();
        startProgressDialog();


        sup_package_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //   package_name = items[position];
                Intent i = new Intent(SupervisorPackageDetails.this, SupervisorShiftAssignment.class);
                //  i.putExtra("package_name",package_name);
                startActivity(i);

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
                        Toast.makeText(SupervisorPackageDetails.this, "No current package added", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject projects_details = new JSONObject(response);

                        JSONArray package_ids_array = projects_details.names();
                        for (int i = 0; i < package_ids_array.length(); i++) {

                            JSONObject project_details = projects_details.getJSONObject(package_ids_array.get(i).toString());
                            //  Log.i("project_details", project_details.toString());
                            fetched_sup_package_name = (String) project_details.get("package_name");
                            //  Log.e("project_name", (String) project_details.get("package_name"));
                            fetched_sup_package_id = package_ids_array.get(i).toString();
                            sup_package_id_list.add(fetched_sup_package_id);
                            fetched_sup_package_location = project_details.getString("package_location");
                            //after modification

                            SupervisorProjectWisePackageListViewItems sup_package = new SupervisorProjectWisePackageListViewItems(fetched_sup_package_name, fetched_sup_package_location);
                            supervisorPackageList.add(0, sup_package);

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
                //  params.put("user_added_id", bundle_project_id);

                //  params.put("module", "project");
                //  params.put("query_type", DataWrapper.QTYPE_O);
                //  params.put("query", "read_projects");


                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onRefresh() {
        fetch_data();
    }

    public void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching project List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

}
