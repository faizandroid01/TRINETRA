package com.master.faiz.trinetra.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.ArrayList;

import Utils.VolleySingleton;

public class AdminProjectReport extends AppCompatActivity {
    Toolbar toolbar;
    ListView contractor_list;
    DatePicker datePicker;
    private ProgressDialog progressDialog;
    ArrayList<String> contractorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_project_report);

        datePicker = (DatePicker) findViewById(R.id.activity_admin_project_report_contractor_date_picker);


        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("Authentication");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        //@GET Method --  fetch the contractorList within admin  .
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching contractor List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        contractorList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                // parse the response and assign it to ArrayList contractorList and then assign array list items to items []  ..
                // @GET Method --  fetch the adminName and his projects corresponding to project id  .

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

       /* final String items[] = new String[project_list.size()];

        for (int i = 0; i < project_list.size(); i++) {

            items[i] = project_list.get(i);

        }*/




        //@Assign After getting the projects list in response,, Assign it to the items array below to display it in listview

        final String items[] = {"C1", "C2", "C3", "C4"};
        contractor_list = (ListView) findViewById(R.id.activity_admin_project_report_contractor_list_view);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        contractor_list.setAdapter(aa);

        contractor_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int date = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                String requiredDate = date + "/" + month + "/" + year;
                Toast.makeText(AdminProjectReport.this, "" + requiredDate, Toast.LENGTH_SHORT).show();
              // for the above date

                String contractor_name = items[position];
                Intent intent = new Intent(AdminProjectReport.this, AdminViewProjectReport.class);
                intent.putExtra("contractor_name", contractor_name);
                intent.putExtra("date",date);
                intent.putExtra("month",month);
                intent.putExtra("year",year);

                startActivity(intent);




            }
        });
    }
}
