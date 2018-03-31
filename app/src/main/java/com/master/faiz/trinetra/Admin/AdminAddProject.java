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

public class AdminAddProject extends AppCompatActivity {

    Toolbar toolbar;
    String admin_project_name;
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
            admin_project_name = b.getString("admin_project_name");

            // fetch the details from data base regarding "admin_project_name" and show it in fields available ,, except the PID , which is auto generated ..


        }


    }

    public void saveProjectDetails(View view) {


        progressDialog = new ProgressDialog(this);

        if (!adminProjectName.getText().toString().isEmpty() && !adminProjectStrtDate.getText().toString().isEmpty() && !adminProjectEndDate.getText().toString().isEmpty() && !adminProjectLocation
                .getText().toString().isEmpty()) {

            progressDialog.setMessage("Wait !! Adding Project ...");
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
                    params.put("", adminProjectName.getText().toString());
                    params.put("", adminProjectStrtDate.getText().toString());
                    params.put("", adminProjectEndDate.getText().toString());
                    params.put("", adminProjectLocation.getText().toString());

                    params.put("module", "");
                    params.put("query", " ");
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
