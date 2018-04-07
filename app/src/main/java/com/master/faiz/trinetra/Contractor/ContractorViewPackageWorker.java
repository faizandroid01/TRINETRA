package com.master.faiz.trinetra.Contractor;

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

import HelperClass.ContractorPackageWiseWorkerListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeContractorPackageWiseWorkerListAdapter;

public class ContractorViewPackageWorker extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    String contractor_package_name;
    String worker_name;
    private ProgressDialog progressDialog;
    ArrayList<String> package_wrkr_list;


    String fetched_package_wrkr_name;
    String fetched_package_wrkr_id;
    String fetched_package_wrkr_type;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView package_worker_listview;
    private SwipeContractorPackageWiseWorkerListAdapter adapter;
    private List<ContractorPackageWiseWorkerListViewItems> packageWorkerList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_view_package_worker);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            contractor_package_name = b.getString("contractor_package_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_package_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        package_worker_listview = (ListView) findViewById(R.id.contractor_package_worker_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        packageWorkerList = new ArrayList<>();
        adapter = new SwipeContractorPackageWiseWorkerListAdapter(this, packageWorkerList);
        package_worker_listview.setAdapter(adapter);
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
        package_wrkr_list = new ArrayList<>();


        package_worker_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //     worker_name = items[position];
                Intent i = new Intent(ContractorViewPackageWorker.this, ContractorAddWorker.class);
                // If want to change then change ,, I am sending the user back to Contactor Add worker panel to see the data he entered ,, there it the data
                // is needed to be fetched corresponding to contractor_name .
                //   i.putExtra("worker_name", worker_name);
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
                        Toast.makeText(ContractorViewPackageWorker.this, "No worker Added", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject packages_details = new JSONObject(response);

                        JSONArray package_ids_array = packages_details.names();
                        for (int i = 0; i < package_ids_array.length(); i++) {

                            JSONObject project_details = packages_details.getJSONObject(package_ids_array.get(i).toString());
                            //  Log.i("project_details", project_details.toString());
                            fetched_package_wrkr_name = (String) project_details.get("worker_name");
                            //  Log.e("project_name", (String) project_details.get("package_name"));
                            fetched_package_wrkr_id = package_ids_array.get(i).toString();
                            package_wrkr_list.add(fetched_package_wrkr_id);
                            fetched_package_wrkr_type = project_details.getString("worker_type");
                            //after modification

                            ContractorPackageWiseWorkerListViewItems c_wrkr = new ContractorPackageWiseWorkerListViewItems(fetched_package_wrkr_name, fetched_package_wrkr_type);
                            packageWorkerList.add(0, c_wrkr);

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

    public void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching worker List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    @Override
    public void onRefresh() {
        fetch_data();
    }
}
