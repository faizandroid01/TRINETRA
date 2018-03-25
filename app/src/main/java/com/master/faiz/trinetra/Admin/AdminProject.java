package com.master.faiz.trinetra.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.master.faiz.trinetra.R;

public class AdminProject extends AppCompatActivity {

    Toolbar toolbar;
    ListView project_listView;
    ArrayAdapter<String> adapter;
    String admin_project_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_project);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(R.string.admin_projects);

        final String[] items = {"P1", "P2", "P3", "P4"};
        project_listView = (ListView) findViewById(R.id.admin_project_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        project_listView.setAdapter(adapter);

        project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                admin_project_name = items[position];
                Intent intent = new Intent(AdminProject.this, AdminPackages.class);
                intent.putExtra("admin_project_name", admin_project_name);
                startActivity(intent);

            }
        });


        project_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(AdminProject.this)
                        .setTitle("Options")
                        .setMessage("Choose Options for Project .")
                        .setIcon(android.R.drawable.dark_header)
                        .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(AdminProject.this, "Delete from Database .", Toast.LENGTH_SHORT).show();

                            }
                        }).setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        admin_project_name = items[position];
                        Intent intent = new Intent(AdminProject.this, AdminAddProject.class);
                        intent.putExtra("admin_project_name", admin_project_name);
                        startActivity(intent);


                    }
                }).create()
                  .show();


                return true;
            }
        });


    }

    public void adminAddProject(View view) {

        startActivity(new Intent(this, AdminAddProject.class));
    }
}

