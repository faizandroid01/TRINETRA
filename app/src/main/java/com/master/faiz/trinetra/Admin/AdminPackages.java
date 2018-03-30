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

public class AdminPackages extends AppCompatActivity {

    Toolbar toolbar;
    ListView package_listView;
    ArrayAdapter<String> adapter;
    String admin_project_name,admin_package_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_packages);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        Bundle b = getIntent().getExtras();
        if (b != null) {
            admin_project_name = b.getString("admin_project_name");

            toolbar = (Toolbar) findViewById(R.id.MyToolbar);
            toolbar.setTitle(admin_project_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        }
        final String[] items = {"Pk 1", "Pk 2", "Pk 3", "Pk 4"};
        package_listView = (ListView) findViewById(R.id.admin_package_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        package_listView.setAdapter(adapter);

        package_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String admin_package_name = items[position];
                Intent intent = new Intent(AdminPackages.this, AdminViewPackage.class);
                intent.putExtra("admin_package_name", admin_package_name);
                startActivity(intent);


            }
        });


        package_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(AdminPackages.this)
                        .setTitle("Options")
                        .setMessage("Choose Options for Project .")
                        .setIcon(android.R.drawable.dark_header)
                        .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(AdminPackages.this, "Delete from Database .", Toast.LENGTH_SHORT).show();

                            }
                        }).setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        admin_package_name = items[position];
                        Intent intent = new Intent(AdminPackages.this, AdminAddPackage.class);
                        intent.putExtra("admin_package_name", admin_package_name);
                        startActivity(intent);


                    }
                }).create()
                        .show();


                return true;
            }
        });



    }

    public void adminAddPackage(View view) {

        startActivity(new Intent(this, AdminAddPackage.class));

    }
}
