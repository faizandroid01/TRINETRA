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

import HelperClass.SupervisorProjectListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeSupervisorProjectListAdapter;

public class SupervisorLogin extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    String project_name;
    private ProgressDialog progressDialog;
    ArrayList<String> sup_project_id_list;

    String fetched_sup_project_name;
    String fetched_sup_project_id;
    String fetched_sup_project_location;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView sup_project_listView;
    private SwipeSupervisorProjectListAdapter adapter;
    private List<SupervisorProjectListViewItems> supervisorProjectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_login);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("PROJECTS");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        sup_project_listView = (ListView) findViewById(R.id.supervisor_project_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        supervisorProjectList = new ArrayList<>();
        adapter = new SwipeSupervisorProjectListAdapter(this, supervisorProjectList);
        sup_project_listView.setAdapter(adapter);
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
        sup_project_id_list = new ArrayList<>();


        sup_project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // project_name = items[position];
                Intent intent = new Intent(SupervisorLogin.this, SupervisorPackageDetails.class);
                //intent.putExtra("project_name",project_name);
                startActivity(intent);

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
                        Toast.makeText(SupervisorLogin.this, "No current projects available", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject projects_details = new JSONObject(response);

                        JSONArray package_ids_array = projects_details.names();
                        for (int i = 0; i < package_ids_array.length(); i++) {

                            JSONObject project_details = projects_details.getJSONObject(package_ids_array.get(i).toString());
                            //  Log.i("project_details", project_details.toString());
                            fetched_sup_project_name = (String) project_details.get("package_name");
                            //  Log.e("project_name", (String) project_details.get("package_name"));
                            fetched_sup_project_id = package_ids_array.get(i).toString();
                            sup_project_id_list.add(fetched_sup_project_id);
                            fetched_sup_project_location = project_details.getString("package_location");
                            //after modification

                            SupervisorProjectListViewItems sup_project = new SupervisorProjectListViewItems(fetched_sup_project_name, fetched_sup_project_location);
                            supervisorProjectList.add(0, sup_project);

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
