package com.master.faiz.trinetra.Contractor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.master.faiz.trinetra.R;

public class ContractorLogin extends AppCompatActivity {

    Toolbar toolbar;
    ListView contractor_project_listView;
    ArrayAdapter<String> adapter;
    String contractor_project_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_login);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(R.string.admin_projects);

        final String items[] = {"CP 1", "CP 2", "CP 3", "CP 4", "CP 5"};
        contractor_project_listView = (ListView) findViewById(R.id.contractor_projects_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        contractor_project_listView.setAdapter(adapter);

        contractor_project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                contractor_project_name = items[position];
                Intent i = new Intent(ContractorLogin.this, ContractorViewPackage.class);
                i.putExtra("contractor_project_name", contractor_project_name);
                startActivity(i);

                contractor_project_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        new AlertDialog.Builder(ContractorLogin.this)
                                .setTitle("Options")
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//DELETE from database

                            }
                        }).create()
                                .show();

                        return true;
                    }
                });

            }
        });


    }

    public void contractorAddWorker(View view) {
        //adds the worker in his own database

        startActivity(new Intent(this, ContractorAddWorker.class));

    }

    public void contractorViewWorker(View view) {
        // view whole worker list from his database
        

    }
}
