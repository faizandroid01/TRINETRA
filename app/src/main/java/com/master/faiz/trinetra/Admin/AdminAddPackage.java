package com.master.faiz.trinetra.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class AdminAddPackage extends AppCompatActivity {


    Toolbar toolbar;
    String admin_package_name;
    EditText packageName, packageStrtDate, packageEndDate, packageLocation;
    private ProgressDialog progressDialog;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_package);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD PACKAGE");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        packageName = (EditText) findViewById(R.id.activity_admin_add_package_package_name);
        packageStrtDate = (EditText) findViewById(R.id.activity_admin_add_package_package_start_date);
        packageEndDate = (EditText) findViewById(R.id.activity_admin_add_package_package_end_date);
        packageLocation = (EditText) findViewById(R.id.activity_admin_add_package_package_location);
        linearLayout = (LinearLayout) findViewById(R.id.acitivity_admin_add_package_linear_layout);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            admin_package_name = b.getString("admin_package_name");

            // fetch the details from data base regarding "admin_project_name" and show it in fields available ....


        }

    }

    public void savePackageDetails(View view) {

        progressDialog = new ProgressDialog(this);

        if (!packageName.getText().toString().isEmpty() && !packageStrtDate.getText().toString().isEmpty() && !packageEndDate.getText().toString().isEmpty() && !packageLocation
                .getText().toString().isEmpty()) {

            progressDialog.setMessage("Wait !! Adding Package ...");
            progressDialog.setCancelable(false);
            progressDialog.show();


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
                            startActivity(new Intent(AdminAddPackage.this, AdminPackages.class));

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
                    params.put("package_name", packageName.getText().toString());
                    params.put("package_start_date", packageStrtDate.getText().toString());
                    params.put("package_end_date", packageEndDate.getText().toString());
                    params.put("package_location", packageLocation.getText().toString());

                    params.put("module", "package");
                    params.put("query", "create_package");
                    params.put("query_type", DataWrapper.QTYPE_I);

                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        } else {
            Snackbar.make(linearLayout, "Fields can't be left blank !", Snackbar.LENGTH_SHORT).show();
        }


    }
}
