package com.master.faiz.trinetra.Contractor;

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

import HelperClass.ContractorWorkerListViewItems;
import Utils.DataWrapper;
import Utils.VolleySingleton;
import adapter.SwipeContractorWorkerListAdapter;

public class ContractorViewWorker extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    String worker_name;
    private ProgressDialog progressDialog;
    ArrayList<String> wrkr_id_list;


    String fetched_worker_name;
    String fetched_worker_id;
    String fetched_worker_type;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView contractor_worker_listView;
    private SwipeContractorWorkerListAdapter adapter;
    private List<ContractorWorkerListViewItems> workerList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_view_worker);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("WORKER LIST");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        wrkr_id_list = new ArrayList<>();

        contractor_worker_listView = (ListView) findViewById(R.id.contractor_worker_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_admin_project_panel);
        workerList = new ArrayList<>();
        adapter = new SwipeContractorWorkerListAdapter(this, workerList);
        contractor_worker_listView.setAdapter(adapter);
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


        contractor_worker_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //    worker_name = items[position];
                Intent i = new Intent(ContractorViewWorker.this, ContractorAddWorker.class);
                // If want to change then change ,, I am sending the user back to Contactor Add worker panel to see the data he entered ,, there it the data
                // is needed to be fetched corresponding to contractor_name .
                //      i.putExtra("worker_name", worker_name);
                startActivity(i);

            }
        });


        contractor_worker_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //    worker_name = items[position];


                new AlertDialog.Builder(ContractorViewWorker.this)
                        .setTitle("options")
                        .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Delete from data base
                            }
                        })
                        .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(ContractorViewWorker.this, ContractorAddWorker.class);
                        //     i.putExtra("worker_name", worker_name);
                        startActivity(i);


                    }
                }).create().show();

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
                        Toast.makeText(ContractorViewWorker.this, "No worker Added", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject contractors_worker_details = new JSONObject(response);

                        JSONArray wrkr_id_array = contractors_worker_details.names();
                        for (int i = 0; i < wrkr_id_array.length(); i++) {

                            JSONObject project_details = contractors_worker_details.getJSONObject(wrkr_id_array.get(i).toString());
                            //  Log.i("project_details", project_details.toString());
                            fetched_worker_name = (String) project_details.get("worker_name");
                            //  Log.e("project_name", (String) project_details.get("package_name"));
                            fetched_worker_id = wrkr_id_array.get(i).toString();
                            wrkr_id_list.add(fetched_worker_id);
                            fetched_worker_type = project_details.getString("worker_type");
                            //after modification

                            ContractorWorkerListViewItems c_wrkr = new ContractorWorkerListViewItems(fetched_worker_name, fetched_worker_type);
                            workerList.add(0, c_wrkr);

                        }

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.e("Contractor Worker", "JSON EXCEPTION");
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
        progressDialog.setMessage(" Fetching package worker List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    @Override
    public void onRefresh() {
        fetch_data();
    }
}
