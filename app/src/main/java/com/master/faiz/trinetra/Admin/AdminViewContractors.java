package com.master.faiz.trinetra.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.master.faiz.trinetra.R;

public class AdminViewContractors extends AppCompatActivity {

    Toolbar toolbar;
    ListView contractor_listview;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_contractors);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("CONTRACTORS");


        final String[] items = {"C 1", "C 2", "C 3", "C 4"};
        contractor_listview = (ListView) findViewById(R.id.contractor_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        contractor_listview.setAdapter(adapter);

        contractor_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String contractor_name = items[position];
                Intent intent = new Intent(AdminViewContractors.this, AdminViewContractorDetails.class);
                intent.putExtra("contractor_name", contractor_name);
                startActivity(intent);
            }
        });
    }

    public void adminAddContractor(View view) {
        startActivity(new Intent(this,AdminAddContractor.class));
    }
}