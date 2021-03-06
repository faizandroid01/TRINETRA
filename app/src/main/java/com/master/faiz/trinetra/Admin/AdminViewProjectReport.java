package com.master.faiz.trinetra.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class AdminViewProjectReport extends AppCompatActivity {

    Toolbar toolbar;
    TextView noOfWorkerpresent, authGenerated, successfullAuth, overallPercentage;
    LinearLayout linearLayout;
    private ProgressDialog progressDialog;
    String contractor_name, date, month, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_project_report);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            contractor_name = b.getString("contractor_name");
            date = b.getString("date");
            month = b.getString("month");
            year = b.getString("year");

            String requiredDate = date + "/" + month + "/" + year;
            Toast.makeText(AdminViewProjectReport.this, "" + requiredDate, Toast.LENGTH_SHORT).show();

        }
        noOfWorkerpresent = (TextView) findViewById(R.id.activity_admin_view_project_report_worker_present);
        authGenerated = (TextView) findViewById(R.id.activity_admin_view_project_report_authentication_generated);
        successfullAuth = (TextView) findViewById(R.id.activity_admin_view_project_report_successfull_authentication);
        overallPercentage = (TextView) findViewById(R.id.activity_admin_view_project_report_overall_record);
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_view_project_report_linear_layout);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        StringRequest stringRequest = new StringRequest(Request.Method.POST, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                progressDialog.dismiss();

               /*     if(status==false)
                    {
                       Snackbar.make(linearLayout , "Failed !!" , Snackbar.LENGTH_SHORT);

                    }else
                        {
                            Snackbar.make(linearLayout , "Details Added Successfully .. " , Snackbar.LENGTH_SHORT);
                             startActivity(new Intent(AdminAddProject.this,AdminProject.class));
                        }
*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Log.i("Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
              /*  params.put("package_id", );
 //                params.put("project_id",);
                params.put("date",);
                params.put("contractor_id",)
*/
                params.put("module", "project");
                params.put("query", "create_project");
                params.put("query_type", DataWrapper.QTYPE_I);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching Details List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        stringRequest = new StringRequest(Request.Method.GET, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

               /*     if(status==false)
                    {
                       Snackbar.make(linearLayout , "Failed !!" , Snackbar.LENGTH_SHORT);

                    }
*/
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


    }
}
