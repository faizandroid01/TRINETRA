package com.master.faiz.trinetra.Contractor;

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
import com.master.faiz.trinetra.R;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class ContractorAddWorker extends AppCompatActivity {
    Toolbar toolbar;

    ArrayAdapter<String> aa;
    Spinner cont_worker_type;
    EditText wrkrName, wrkrAadharNo;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    String items[] = {"SKILLED/TEMPORARY", "SKILLED/PERMANENT", "SKILLED/DAILY WAGE", "SEMI-SKILLED/TEMPORARY", "SEMI-SKILLED/PERMANENT", "SEMI-SKILLED/DAILY WAGE", "UNSKILLED/TEMPORARY", "UNSKILLED/PERNANENT", "UNSKILLED/DAILY WAGE"};
    String spinner_selected_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_add_worker);

        wrkrName = (EditText) findViewById(R.id.activity_contractor_add_worker_worker_name);
        wrkrAadharNo = (EditText) findViewById(R.id.activity_contractor_add_worker_aadhar_no);
        linearLayout = (LinearLayout) findViewById(R.id.activity_contractor_add_worker_linear_layout);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            String worker_name = b.getString("worker_name");

            Toast.makeText(this, worker_name, Toast.LENGTH_SHORT).show();
            // fetch the data and assign to respective editext
        }
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("ADD WORKKER");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        cont_worker_type = (Spinner) findViewById(R.id.activity_contractor_add_worker_worker_type_spinner);
        aa = new ArrayAdapter<String>(ContractorAddWorker.this, android.R.layout.simple_dropdown_item_1line, items);
        cont_worker_type.setAdapter(aa);


    }

    public void addWorker(View view) {

        //ADD the data from the above field into the data base  ..
        // And if it is already updated its needed to be updated

        progressDialog = new ProgressDialog(this);
        if (cont_worker_type.getSelectedItem().toString().equals(items[0])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_1;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[1])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_2;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[2])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_3;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[3])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_4;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[4])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_5;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[5])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_6;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[6])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_7;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[7])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_8;
        }
        if (cont_worker_type.getSelectedItem().toString().equals(items[8])) {
            spinner_selected_item = DataWrapper.WORKER_TYPE_9;
        }


        if (!wrkrName.getText().toString().isEmpty() && !wrkrAadharNo.getText().toString().isEmpty()) {

            progressDialog.setMessage("Wait .. Registering Contractor ...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

               /*     if(status==false)
                    {
                       Snackbar.make(linearLayout , "Failed" , Snackbar.LENGTH_SHORT);
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
                    params.put("", wrkrName.getText().toString());
                    params.put("", spinner_selected_item);

                    params.put("", wrkrAadharNo.getText().toString());

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

    }
}
