package com.master.faiz.trinetra.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import Utils.VolleySingleton;

public class AdminViewProjectReport extends AppCompatActivity {

    Toolbar toolbar;
    TextView noOfWorkerpresent, authGenerated, successfullAuth, overallPercentage;
    LinearLayout linearLayout;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_project_report);

        Bundle b = getIntent().getExtras();
        String contractor_name = b.getString("contractor_name");
        String date = b.getString("date");
        String month = b.getString("month");
        String year = b.getString("year");

        String requiredDate = date + "/" + month + "/" + year;

        noOfWorkerpresent = (TextView) findViewById(R.id.activity_admin_view_project_report_worker_present);
        authGenerated = (TextView) findViewById(R.id.activity_admin_view_project_report_authentication_generated);
        successfullAuth = (TextView) findViewById(R.id.activity_admin_view_project_report_successfull_authentication);
        overallPercentage = (TextView) findViewById(R.id.activity_admin_view_project_report_overall_record);
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_view_project_report_linear_layout);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching Details List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, null, new Response.Listener<String>() {
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
