package com.master.faiz.trinetra.Supervisor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.ArrayList;

import Utils.VolleySingleton;

public class SupervisorPackageDetails extends AppCompatActivity {


    Toolbar toolbar;
    ListView sup_package_listView;
    String package_name;
    String project_name;
    private ProgressDialog progressDialog;
    ArrayList<String> sup_package_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_package_details);

        sup_package_listView = (ListView) findViewById(R.id.activity_supervisor_login_package_listview);

        Bundle b=getIntent().getExtras();
        if(b!=null){
            project_name =  b.getString("project_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(project_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Fetching package List ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        sup_package_list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                progressDialog.dismiss();
                // parse the response and assign it to ArrayList package_list and then assign array list items to items []  ..

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

       /* final String items[] = new String[sup_package_list.size()];

        for (int i = 0; i < sup_package_list.size(); i++) {

            items[i] = sup_package_list.get(i);

        }*/

        final String items[] = {"pk1", "pk2", "pk3"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(SupervisorPackageDetails.this,android.R.layout.simple_dropdown_item_1line,items);
        sup_package_listView.setAdapter(aa);

        sup_package_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                package_name = items[position];
                Intent i = new Intent(SupervisorPackageDetails.this,SupervisorShiftAssignment.class);
                i.putExtra("package_name",package_name);
                startActivity(i);

            }
        });


    }
}
