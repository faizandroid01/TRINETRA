package com.master.faiz.trinetra;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText userName, userEmail, userPassword, userAadhar;
    Spinner spinner;
    String spinner_items[] = {"ADMIN", "CONTRACTOR", "SUPERVISOR"};
    String spinner_val;
    private ProgressDialog progressDialog;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        userName = (EditText) findViewById(R.id.activity_sign_up_user_name);
        userEmail = (EditText) findViewById(R.id.activity_sign_up_email);
        userPassword = (EditText) findViewById(R.id.activity_sign_up_password);
        userAadhar = (EditText) findViewById(R.id.activity_sign_up_aadhar_id);
        spinner = (Spinner) findViewById(R.id.user_signup_type_spinner);
        linearLayout = (LinearLayout) findViewById(R.id.activity_sign_up_linear_layout);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sign Up");
        toolbar.setSubtitle("Mecon Ltd.");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinner_items);
        spinner.setAdapter(aa);


    }

    public void userSignUp(View view) {


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait !! Adding Project ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Taking value from spinner
        String spinner_text = spinner.getSelectedItem().toString();
        if (spinner_text.equals(spinner_items[0])) {
            spinner_val = DataWrapper.AC_ADMIN;
        } else if (spinner_text.equals(spinner_items[1])) {
            spinner_val = DataWrapper.AC_CONTRACTOR;
        } else if (spinner_text.equals(spinner_items[2])) {
            spinner_val = DataWrapper.AC_SUPERVISOR;

        }


        StringRequest request = new StringRequest(Request.Method.POST, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                if (response == "") {
                    Snackbar.make(linearLayout, "Sign up Successfull ..", Snackbar.LENGTH_SHORT);
                } else {
                    Snackbar.make(linearLayout, "Sign Up Failed !!", Snackbar.LENGTH_SHORT);
                }


                Log.i("", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("", "vollerr" + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("name", userName.getText().toString());
                params.put("email", userEmail.getText().toString());
                params.put("password", userPassword.getText().toString());
                params.put("user_type", spinner_val);
                params.put("aadhar_id", userAadhar.getText().toString());

                params.put("module", DataWrapper.QMODULE_USER);
                params.put("query", DataWrapper.Q_CREATE_ACCOUNT);
                params.put("query_type", DataWrapper.QTYPE_I);

                return params;

            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);


//    startActivity(new Intent(this,MainActivity.class));


    }


}
