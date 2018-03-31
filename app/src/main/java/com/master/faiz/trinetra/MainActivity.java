package com.master.faiz.trinetra;

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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.Admin.AdminProject;
import com.master.faiz.trinetra.Contractor.ContractorLogin;
import com.master.faiz.trinetra.Supervisor.SupervisorLogin;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText userName, password;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        userName = (EditText) findViewById(R.id.activity_main_username);
        password = (EditText) findViewById(R.id.activity_main_password);
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
    }

    public void userLogin(View v) {


        if (!userName.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            progressDialog.setMessage(" Signing In user ...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    progressDialog.dismiss();

               /*     if(status==false)
                    {
                       Snackbar.make(linearLayout , "Sign In failed!! Try Again .." , Snackbar.LENGTH_SHORT);

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
                    params.put("", userName.getText().toString());
                    params.put("", password.getText().toString());

                    params.put("module", "");
                    params.put("query", "");
                    params.put("query_type", DataWrapper.QTYPE_I);


                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        } else {
            Snackbar.make(linearLayout, "Email can't be left blank !", Snackbar.LENGTH_SHORT).show();
        }


        String a = userName.getText().toString();

        if (a.equalsIgnoreCase("a")) {
            //check the login and password (related to either admin/cont/supervisor) and transfer the control to user login screen.
            startActivity(new Intent(this, AdminProject.class));
        } else if (a.equalsIgnoreCase("s")) {
            startActivity(new Intent(this, SupervisorLogin.class));

        } else if (a.equalsIgnoreCase("c")) {
            startActivity(new Intent(this, ContractorLogin.class));

        }
    }

    public void userRegister(View v) {
        Toast.makeText(this, "Sign Up Fun", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, SignUpActivity.class));


    }
}
