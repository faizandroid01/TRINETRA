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

public class AdminViewPackage extends AppCompatActivity {

    Toolbar toolbar;
    String admin_package_name;
    TextView packageName, packageStrtDt, packageEndDt, packageLocation;
    LinearLayout linearLayout;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_package);

        Bundle b = getIntent().getExtras();
        if (b != null)
            admin_package_name = b.getString("admin_package_name");

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(admin_package_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        packageName = (TextView) findViewById(R.id.activity_admin_view_package_package_name);
        packageStrtDt = (TextView) findViewById(R.id.activity_admin_view_package_package_start_date);
        packageEndDt = (TextView) findViewById(R.id.activity_admin_view_package_package_end_date);
        packageLocation = (TextView) findViewById(R.id.activity_admin_view_package_package_location);
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_view_package_linear_layout);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching Details  ...");
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

    public void viewContractors(View v) {
        startActivity(new Intent(this, AdminViewContractors.class));
    }

    public void packageReport(View v) {
        startActivity(new Intent(AdminViewPackage.this, AdminProjectReport.class));
    }

}
