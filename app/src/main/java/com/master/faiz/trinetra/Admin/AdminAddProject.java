package com.master.faiz.trinetra.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class AdminAddProject extends AppCompatActivity {

    Toolbar toolbar;
    String user_id, user_name;
    EditText adminProjectName, adminProjectStrtDate, adminProjectEndDate, adminProjectLocation;
    private ProgressDialog progressDialog;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_project);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD PROJECT");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        adminProjectName = (EditText) findViewById(R.id.activity_admin_add_project_project_name);
        adminProjectStrtDate = (EditText) findViewById(R.id.activity_admin_add_project_project_start_date);
        adminProjectEndDate = (EditText) findViewById(R.id.activity_admin_add_project_project_end_date);
        adminProjectLocation = (EditText) findViewById(R.id.activity_admin_add_project_project_location);
        linearLayout = (LinearLayout) findViewById(R.id.acitivity_admin_add_project_linearLayout);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            user_id = b.getString("user_id");
            user_name = b.getString("user_name");
        }


        // fetch the details from data base regarding "admin_project_name" and show it in fields available ,, except the PID , which is auto generated ..


    }


    public void saveProjectDetails(View view) {


        progressDialog = new ProgressDialog(this);

        if (!adminProjectName.getText().toString().isEmpty() && !adminProjectStrtDate.getText().toString().isEmpty() && !adminProjectEndDate.getText().toString().isEmpty() && !adminProjectLocation
                .getText().toString().isEmpty()) {

            progressDialog.setMessage("Wait !! Adding Project ...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Log.i("line no 81", response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("status")) {
                            int status = Integer.parseInt(jsonObject.getString("status"));

                            if (status == -1) {
                                Snackbar.make(linearLayout, "Project Not Added .. Try Again", Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(linearLayout, "Project Added", Snackbar.LENGTH_LONG).show();
                                Intent i = new Intent(AdminAddProject.this, AdminProject.class);
                                i.putExtra("user_id", user_id);
                                i.putExtra("user_name", user_name);


                                startActivity(i);
                                finish();

                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                    Log.i("Error: ", "some volley error");


                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("project_name", adminProjectName.getText().toString());
                    params.put("project_start_date", adminProjectStrtDate.getText().toString());
                    params.put("project_end_date", adminProjectEndDate.getText().toString());
                    params.put("project_location", adminProjectLocation.getText().toString());
                    params.put("user_id", user_id);

                    params.put("module", "project");
                    params.put("query", "create_project");
                    params.put("query_type", DataWrapper.QTYPE_I);

                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        } else {
            Snackbar.make(linearLayout, "Fields can't be left blank !", Snackbar.LENGTH_SHORT).show();
        }


    }

    public void startDate(View view) {
    }

    public void endDate(View view) {
    }
}
