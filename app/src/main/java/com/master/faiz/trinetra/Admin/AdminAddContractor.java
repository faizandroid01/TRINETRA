package com.master.faiz.trinetra.Admin;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.HashMap;
import java.util.Map;

import Utils.VolleySingleton;

public class AdminAddContractor extends AppCompatActivity {

    Toolbar toolbar;
    EditText contractorEmail;
    LinearLayout linearLayout;

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

        if (contractorEmail != null) {
            final String email = contractorEmail.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("", email);


                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        } else
            Snackbar.make(linearLayout, "Email can't be left blank !", Snackbar.LENGTH_SHORT);

    }
}
