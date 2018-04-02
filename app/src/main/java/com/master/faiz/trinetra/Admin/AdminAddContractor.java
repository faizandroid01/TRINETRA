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

public class AdminAddContractor extends AppCompatActivity {

    Toolbar toolbar;
    EditText contractorEmail;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_contractor);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));
        toolbar.setTitle("ADD CONTRACTOR");

        contractorEmail = (EditText) findViewById(R.id.activity_admin_add_contractor_email_id);
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_add_contractor_linearLayout);
    }

    public void adminAddContractorDetails(View view) {

        progressDialog = new ProgressDialog(this);


        if (!contractorEmail.getText().toString().isEmpty()) {

            progressDialog.setMessage("Wait .. Registering Contractor ...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            final String email = contractorEmail.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    progressDialog.dismiss();

               /*     if(status==false)
                    {
                       Snackbar.make(linearLayout , "Sign Up failed!! Try Again .." , Snackbar.LENGTH_SHORT);

                    }else
                        {
                            Snackbar.make(linearLayout , "Sign Up Successful .." , Snackbar.LENGTH_SHORT);
                            startActivity(new Intent(AdminAddContractor.this, MainActivity.class));

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
                 /*   params.put("user_added_email", email);
                    params.put("user_adder_id", ); // signed in admin ID
                    params.put("project_id",);     //  current project id
*/
                    params.put("module", "user_project");
                    params.put("query", "add_user_project");
                    params.put("query_type", DataWrapper.QTYPE_I);


                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        } else {
            Snackbar.make(linearLayout, "Email can't be left blank !", Snackbar.LENGTH_SHORT).show();
        }
    }
}
