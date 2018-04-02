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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.Tools;
import Utils.VolleySingleton;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText userName, password;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    Intent activity_transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));
        progressDialog = new ProgressDialog(this);

        userName = (EditText) findViewById(R.id.activity_main_username);
        password = (EditText) findViewById(R.id.activity_main_password);
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
    }

    public void userLogin(View v) {


        if (!userName.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            progressDialog.setMessage(" Signing In user ...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    progressDialog.dismiss();

                    JSONObject res;
                    try {
                        res = new JSONObject(response);
                        // Log.e("response", response);
                        if (res.has("status")) {
                            String status = (String) res.get("status");
                            Snackbar.make(linearLayout, "Invalid Email or Password !", Snackbar.LENGTH_SHORT).show();
                        } else {
                            JSONObject xx = res.getJSONObject(userName.getText().toString().trim());// new JSONObject(userName.getText().toString().trim());

                            String user_id = (String) xx.get("user_id");
                            //     Log.i("line no 128", user_id);
                            String user_passsword = (String) xx.get("password");
                            String user_aadhar = (String) xx.get("aadhar_id");
                            String user_name = (String) xx.get("name");
                            String user_type = (String) xx.get("user_type");


                            if (user_type.equals(DataWrapper.AC_ADMIN)) {

                                //    Log.i("line no 126", "AC_admin compared");
                                activity_transfer = new Intent(MainActivity.this, AdminProject.class);
                                activity_transfer.putExtra("user_id", user_id);
                                activity_transfer.putExtra("user_name", user_name);
                                activity_transfer.putExtra("user_aadhar", user_aadhar);
                                activity_transfer.putExtra("user_type", user_type);
                            }
                            if (user_type.equals(DataWrapper.AC_CONTRACTOR)) {
                                activity_transfer = new Intent(MainActivity.this, ContractorLogin.class);
                            }
                            if (user_type.equals(DataWrapper.AC_SUPERVISOR)) {
                                activity_transfer = new Intent(MainActivity.this, SupervisorLogin.class);
                            }


                            startActivity(activity_transfer);
                            Toast.makeText(MainActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                            Snackbar.make(linearLayout, "Login Successful !!", Snackbar.LENGTH_LONG);

                        }
                    } catch (JSONException e) {
                        Log.e("line 96", "JSON EX");
                    }

                    //    Log.i("", response);


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
                    params.put("email", userName.getText().toString());
                    params.put("password", Tools.salty(password.getText().toString()));

                    params.put("module", "user");
                    params.put("query", "sign_in");
                    params.put("query_type", DataWrapper.QTYPE_O);


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
