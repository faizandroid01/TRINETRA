package com.master.faiz.trinetra.Admin;

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

import HelperClass.AdminPackageWiseContractorListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeAdminPackageWiseContractorListAdapter;

public class AdminViewContractors extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    private ProgressDialog progressDialog;
    ArrayList<String> contract_ids_list;

    String fetched_contractor_name;
    String fetched_contractor_id;


    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView contractor_listview;
    private SwipeAdminPackageWiseContractorListAdapter adapter;
    private List<AdminPackageWiseContractorListViewItems> adminPackageWiseContractorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_contractors);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("CONTRACTORS");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        startProgressDialog();

        contract_ids_list = new ArrayList<>();


        contractor_listview = (ListView) findViewById(R.id.admin_project_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        adminPackageWiseContractorList = new ArrayList<>();
        adapter = new SwipeAdminPackageWiseContractorListAdapter(this, adminPackageWiseContractorList);
        contractor_listview.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetch_data();
                                    }
                                }
        );


        contractor_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String contractor_name = items[position];
                Intent intent = new Intent(AdminViewContractors.this, AdminViewContractorDetails.class);
                //   intent.putExtra("contractor_name", contractor_name);
                startActivity(intent);
            }
        });
    }

    public void adminAddContractor(View view) {
        startActivity(new Intent(this, AdminAddContractor.class));
    }


    public void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching contractor List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    @Override
    public void onRefresh() {
        fetch_data();
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
                        Toast.makeText(AdminViewContractors.this, "No contractor are yet employed", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject contractors_details = new JSONObject(response);

                        JSONArray contractor_ids_array = contractors_details.names();
                        for (int i = 0; i < contractor_ids_array.length(); i++) {

                            JSONObject project_details = contractors_details.getJSONObject(contractor_ids_array.get(i).toString());
                            // Log.i("project_details", project_details.toString());
                            fetched_contractor_name = (String) project_details.get("project_name");
                            //    Log.e("project_name", (String) project_details.get("project_name"));
                            fetched_contractor_id = contractor_ids_array.get(i).toString();
                            contract_ids_list.add(fetched_contractor_id);

                            AdminPackageWiseContractorListViewItems a_cont_lv = new AdminPackageWiseContractorListViewItems(fetched_contractor_name);
                            adminPackageWiseContractorList.add(0, a_cont_lv);

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
                //    params.put("user_added_id", bundle_user_id);

                params.put("module", "project");
                params.put("query_type", DataWrapper.QTYPE_O);
                params.put("query", "read_projects");


                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}