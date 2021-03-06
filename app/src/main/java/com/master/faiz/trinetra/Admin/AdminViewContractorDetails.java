package com.master.faiz.trinetra.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import Utils.VolleySingleton;

public class AdminViewContractorDetails extends AppCompatActivity {

    Toolbar toolbar;
    private ProgressDialog progressDialog;
    TextView contractorName, nOSkilled, nOSemiSkilled, permanentStatus, temporaryStatus;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_contractor_details);

        Bundle b = getIntent().getExtras();
        String contractor_name = b.getString("contractor_name");

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(contractor_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        contractorName = (TextView) findViewById(R.id.activity_admin_view_contractor_details_contractor_name);
        nOSkilled = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_skilled_workers);
        nOSemiSkilled = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_semiskilled_workers);
        permanentStatus = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_permanent_workers);
        temporaryStatus = (TextView) findViewById(R.id.activity_admin_view_contractor_details_number_of_temporary_workers);
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_view_contractor_details_linear_layout);


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

    public void scheduleAttendance(View v) {

        startActivity(new Intent(this, AdminScheduleAttendance.class));

    }
}
