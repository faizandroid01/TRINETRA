package com.master.faiz.trinetra.Contractor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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

import HelperClass.ContractorProjectListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeContractorProjectListAdapter;

public class ContractorLogin extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    String contractor_project_name;
    ArrayList<String> cont_project_id_list;
    private ProgressDialog progressDialog;

    String fetched_cont_project_name;
    String fetched_cont_project_id;
    String fetched_cont_project_location;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView contractor_project_listView;
    private SwipeContractorProjectListAdapter adapter;
    private List<ContractorProjectListViewItems> contractorProjectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_login);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(R.string.admin_projects);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        startProgressDialog();
        cont_project_id_list = new ArrayList<>();


        contractor_project_listView = (ListView) findViewById(R.id.contractor_projects_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        contractorProjectList = new ArrayList<>();
        adapter = new SwipeContractorProjectListAdapter(this, contractorProjectList);
        contractor_project_listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetch_data();
                                    }
                                }
        );

        contractor_project_listView.setAdapter(adapter);

        contractor_project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                contractor_project_name = items[position];
                Intent i = new Intent(ContractorLogin.this, ContractorViewPackage.class);
                //i.putExtra("contractor_project_name", contractor_project_name);
                startActivity(i);

                contractor_project_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        new AlertDialog.Builder(ContractorLogin.this)
                                .setTitle("Options")
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//DELETE from database

                            }
                        }).create()
                                .show();

                        return true;
                    }
                });

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
                        Toast.makeText(ContractorLogin.this, "No current Projects available", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject projects_details = new JSONObject(response);

                        JSONArray cont_project_ids_array = projects_details.names();
                        for (int i = 0; i < cont_project_ids_array.length(); i++) {

                            JSONObject project_details = projects_details.getJSONObject(cont_project_ids_array.get(i).toString());
                            Log.i("project_details", project_details.toString());
                            fetched_cont_project_name = (String) project_details.get("project_name");
                            Log.e("project_name", (String) project_details.get("project_name"));
                            fetched_cont_project_id = cont_project_ids_array.get(i).toString();
                            cont_project_id_list.add(fetched_cont_project_id);
                            fetched_cont_project_location = project_details.getString("project_location");
                            //after modification

                            ContractorProjectListViewItems c_project = new ContractorProjectListViewItems(fetched_cont_project_name, fetched_cont_project_location);
                            contractorProjectList.add(0, c_project);

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
                //   params.put("user_added_id", bundle_project_id);

                params.put("module", "project");
                params.put("query_type", DataWrapper.QTYPE_O);
                params.put("query", "read_projects");


                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    public void contractorAddWorker(View view) {
        //adds the worker in his own database

        startActivity(new Intent(this, ContractorAddWorker.class));

    }


    public void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching project List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    public void contractorViewWorker(View view) {
        // view whole worker list from his database


    }

    @Override
    public void onRefresh() {

    }
}
